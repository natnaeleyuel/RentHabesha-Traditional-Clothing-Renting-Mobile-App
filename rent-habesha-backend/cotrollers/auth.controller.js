import User from '../models/User.js';
import bcrypt from 'bcryptjs';
import isEmailValid from '../utils/validators.js';
import jwt from 'jsonwebtoken';
import BlacklistedToken from '../models/BlacklistedToken.js';

export const registerUser = async (req, res) => {
    try {
        const { name, email, password, role, phone} = req.body;

        const { valid, reason, validators } = await isEmailValid(email);
        if(!valid) {
            return res.status(400).json({
                message: 'Invalid email address',
                reason: validators[reason]
            });
        }

        const existingUser = await User.findOne({ email });
        if(existingUser) {
            return res.status(400).json({ message: 'Email already in use' });
        }

        const hash = bcrypt.hashSync(password, 10);

        const userData = {
            name,
            email,
            hash,
            phone,
            role: role || 'renter'
        };

        const user = new User(userData);
        await user.save();

        const userResponse = {
            _id: user._id,
            name: user.name,
            email: user.email,
            role: user.role,
            phone: user.phone,
            createdAt: user.createdAt
        };

        res.status(201).json({
            success: true,
            data: userResponse
        });

    } catch (error) {
        console.error('Registration error:', error);

        if (req.file) {
            const fs = await import('fs');
            fs.unlinkSync(req.file.path);
        }

        res.status(400).json({
            success: false,
            message: 'Registration failed',
            error: error.message
        });
    }
};

export const loginUser = async (req, res) => {
    try {
        const { email, password } = req.body;

        if (!email || !password) {
            return res.status(400).json({ message: 'Email and password are required' });
        }

        const user = await User.findOne({ email });
        if (!user) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }

        const isMatch = bcrypt.compareSync(password, user.hash);
        if (!isMatch) {
            return res.status(401).json({ message: 'Invalid credentials' });
        }

        const token = jwt.sign(
            {
                id: user._id,
                email: user.email,
                role: user.role
            },
            process.env.SECRET,
            { expiresIn: '8h' }
        );

        const response = {
            user: {
                id: user._id,
                name: user.name,
                email: user.email,
                role: user.role
            },
            token
        };

        res.status(200).json(response);
    } catch (error) {
        console.error('Login error:', error);
        res.status(500).json({
            message: 'Login failed',
            error: error.message
        });
    }
};

export const logoutUser = async (req, res) => {
    try {
        const authHeader = req.headers.authorization;

        if (!authHeader || !authHeader.startsWith('Bearer ')) {
            return res.status(401).json({
                message: 'Authorization token required'
            });
        }

        const token = authHeader.split(' ')[1];

        const decoded = jwt.verify(token, process.env.JWT_SECRET);

        const existingToken = await BlacklistedToken.findOne({ token });
        if (existingToken) {
            return res.status(400).json({
                message: 'Token already invalidated'
            });
        }

        const expiresAt = new Date(decoded.exp * 1000);
        await BlacklistedToken.create({
            token,
            expiresAt,
            userId: decoded.id
        });

        res.status(200).json({
            success: true,
            message: 'Successfully logged out'
        });

    } catch (error) {
        console.error('Logout error:', error);

        if (error.name === 'JsonWebTokenError') {
            return res.status(401).json({
                message: 'Invalid token'
            });
        }

        if (error.name === 'TokenExpiredError') {
            return res.status(401).json({
                message: 'Token already expired'
            });
        }

        res.status(500).json({
            message: 'Logout failed',
            error: error.message
        });
    }
};