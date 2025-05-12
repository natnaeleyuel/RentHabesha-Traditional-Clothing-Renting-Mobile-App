import ClothingItem from '../models/ClothingItem.js';
import { singleUpload } from '../utils/upload.js';

export const addClothingItem = async (req, res) => {
    singleUpload(req, res, async (err) => {
        try {
            if (err) {
                return res.status(400).json({
                    message: 'Image upload failed',
                    error: err.message
                });
            }

            const authHeader = req.headers.authorization;
            if (!authHeader) {
                return res.status(401).json({ message: 'Authorization required' });
            }

            const token = authHeader.split(' ')[1];
            const decoded = jwt.verify(token, process.env.JWT_SECRET);

            const blacklistedToken = await BlacklistedToken.findOne({ token });
            if (blacklistedToken) {
                return res.status(401).json({ message: 'Token revoked' });
            }

            const { title, description, pricePerDay, type, location } = req.body;

            if (!title || !description || !pricePerDay || !type || !location) {
                return res.status(400).json({ message: 'Missing required fields' });
            }

            const clothingItem = new ClothingItem({
                title,
                description,
                owner: user._id,
                pricePerDay,
                type,
                location,
                images: req.file ? [`/uploads/${req.file.filename}`] : []
            });

            await clothingItem.save();

            const response = clothingItem.toObject();
            delete response.__v;

            res.status(201).json(response);

        } catch (error) {
            console.error('Add clothing error:', error);

            if (req.file) {
                fs.unlinkSync(req.file.path);
            }

            if (error.name === 'ValidationError') {
                return res.status(400).json({
                    message: 'Validation failed',
                    errors: error.errors
                });
            }

            res.status(500).json({
                message: 'Failed to add clothing item',
                error: error.message
            });
        }
    });
};

export const getAllClothingItems = async (req, res) => {
    try {
        const {
            type,
            size,
            location,
            search,
            page = 1,
            limit = 10
        } = req.query;

        const filter = { availability: true };

        if (type) filter.type = type;
        if (size) filter.size = size;
        if (location) filter.location = new RegExp(location, 'i');

        if (search) {
            filter.$or = [
                { title: new RegExp(search, 'i') },
                { description: new RegExp(search, 'i') }
            ];
        }

        const skip = (page - 1) * limit;

        const total = await ClothingItem.countDocuments(filter);

        const items = await ClothingItem.find(filter)
            .populate('owner', 'name email phone')
            .sort({ createdAt: -1 })
            .skip(skip)
            .limit(Number(limit));

        res.status(200).json({
            success: true,
            count: items.length,
            total,
            page: Number(page),
            pages: Math.ceil(total / limit),
            data: items
        });
    } catch (error) {
        console.error('Get clothing items error:', error);
        res.status(500).json({
            success: false,
            message: 'Failed to fetch clothing items',
            error: error.message
        });
    }
};


export const getClothingItemById = async (req, res) => {
    try {
        if (!mongoose.Types.ObjectId.isValid(req.params.id)) {
            return res.status(400).json({
                success: false,
                message: 'Invalid clothing item ID'
            });
        }

        const item = await ClothingItem.findById(req.params.id)
            .populate('owner', 'name email phone rating')
            .populate({
                path: 'reviews',
                populate: {
                    path: 'user',
                    select: 'name profilePhoto'
                }
            });

        if (!item) {
            return res.status(404).json({
                success: false,
                message: 'Clothing item not found'
            });
        }

        if (item.reviews && item.reviews.length > 0) {
            const avgRating = item.reviews.reduce((sum, review) => sum + review.rating, 0) / item.reviews.length;
            item.avgRating = parseFloat(avgRating.toFixed(1));
        }

        const relatedItems = await ClothingItem.find({
            owner: item.owner._id,
            _id: { $ne: item._id },
            availability: true
        })
        .limit(4)
        .select('title images pricePerDay');

        res.status(200).json({
            success: true,
            data: {
                ...item.toObject(),
                relatedItems
            }
        });

    } catch (error) {
        console.error('Get clothing item error:', error);
        res.status(500).json({
            success: false,
            message: 'Failed to fetch clothing item',
            error: error.message
        });
    }
};


export const updateClothingItem = async (req, res) => {
    singleUpload(req, res, async (err) => {
        try {
            if (err) {
                return res.status(400).json({
                    success: false,
                    message: 'Image upload failed',
                    error: err.message
                });
            }

            const authHeader = req.headers.authorization;
            if (!authHeader) {
                return res.status(401).json({
                    success: false,
                    message: 'Authorization required'
                });
            }

            const token = authHeader.split(' ')[1];
            const decoded = jwt.verify(token, process.env.JWT_SECRET);

            const blacklistedToken = await BlacklistedToken.findOne({ token });
            if (blacklistedToken) {
                return res.status(401).json({
                    success: false,
                    message: 'Token revoked'
                });
            }

            const user = await User.findById(decoded.id);
            const clothingItem = await ClothingItem.findById(req.params.id);
            if (!clothingItem) {
                return res.status(404).json({
                    success: false,
                    message: 'Clothing item not found'
                });
            }

            if (clothingItem.owner.toString() !== user._id.toString() || user.role !== 'admin') {
                return res.status(403).json({
                    success: false,
                    message: 'Not authorized to update this item'
                });
            }

            const { title, description, pricePerDay, type, location, availability } = req.body;

            let oldImage = null;

            if (title) clothingItem.title = title;
            if (description) clothingItem.description = description;
            if (pricePerDay) clothingItem.pricePerDay = pricePerDay;
            if (size) clothingItem.size = size;
            if (type) clothingItem.type = type;
            if (location) clothingItem.location = location;
            if (availability !== undefined) clothingItem.availability = availability;

            if (req.file) {
                oldImage = clothingItem.images[0];
                clothingItem.images = [`/uploads/${req.file.filename}`];
            }

            const updatedItem = await clothingItem.save();

            if (oldImage) {
                const fs = await import('fs');
                const path = await import('path');
                const filePath = path.join(__dirname, '../../uploads', oldImage.split('/').pop());

                fs.unlink(filePath, (err) => {
                    if (err) console.error('Error deleting old image:', err);
                });
            }

            res.status(200).json({
                success: true,
                data: updatedItem
            });

        } catch (error) {
            console.error('Update clothing error:', error);

            if (req.file) {
                const fs = await import('fs');
                fs.unlinkSync(req.file.path);
            }

            if (error.name === 'ValidationError') {
                return res.status(400).json({
                    success: false,
                    message: 'Validation failed',
                    errors: error.errors
                });
            }

            if (error.name === 'CastError') {
                return res.status(400).json({
                    success: false,
                    message: 'Invalid clothing item ID'
                });
            }

            res.status(500).json({
                success: false,
                message: 'Failed to update clothing item',
                error: error.message
            });
        }
    });
};


export const deleteClothingItem = async (req, res) => {
    try {
        if (!mongoose.Types.ObjectId.isValid(req.params.id)) {
            return res.status(400).json({
                success: false,
                message: 'Invalid clothing item ID'
            });
        }

        const authHeader = req.headers.authorization;
        if (!authHeader) {
            return res.status(401).json({
                success: false,
                message: 'Authorization token required'
            });
        }

        const token = authHeader.split(' ')[1];
        const decoded = jwt.verify(token, process.env.JWT_SECRET);

        const blacklistedToken = await BlacklistedToken.findOne({ token });
        if (blacklistedToken) {
            return res.status(401).json({
                success: false,
                message: 'Token revoked'
            });
        }

        const user = await User.findById(decoded.id);
        if (!user || user.role !== 'owner' || user.role !== 'admin') {
            return res.status(403).json({
                success: false,
                message: 'Only clothing owners and admin can delete items'
            });
        }

        const clothingItem = await ClothingItem.findById(req.params.id);
        if (!clothingItem) {
            return res.status(404).json({
                success: false,
                message: 'Clothing item not found'
            });
        }

        if (clothingItem.owner.toString() !== user._id.toString()) {
            return res.status(403).json({
                success: false,
                message: 'Not authorized to delete this item'
            });
        }

        const imagesToDelete = clothingItem.images;

        await clothingItem.deleteOne();

        if (imagesToDelete && imagesToDelete.length > 0) {
            const fs = await import('fs');
            const path = await import('path');

            imagesToDelete.forEach(imageUrl => {
                const filename = imageUrl.split('/').pop();
                const filePath = path.join(__dirname, '../../uploads', filename);

                fs.unlink(filePath, err => {
                    if (err) console.error('Error deleting image:', err);
                });
            });
        }

        await Renting.deleteMany({ clothingItem: req.params.id });

        res.status(200).json({
            success: true,
            message: 'Clothing item deleted successfully'
        });

    } catch (error) {
        console.error('Delete clothing error:', error);

        if (error.name === 'JsonWebTokenError') {
            return res.status(401).json({
                success: false,
                message: 'Invalid token'
            });
        }

        if (error.name === 'CastError') {
            return res.status(400).json({
                success: false,
                message: 'Invalid clothing item ID'
            });
        }

        res.status(500).json({
            success: false,
            message: 'Failed to delete clothing item',
            error: error.message
        });
    }
};


export const updateItemAvailability = async (req, res) => {
  try {
    const { clothingItemId } = req.params;
    const { available } = req.body;
    const userId = req.user.id;

    const item = await ClothingItem.findById(clothingItemId);

    if (!item) {
      return res.status(404).json({ message: 'Clothing item not found' });
    }

    if (item.owner.toString() !== userId) {
      return res.status(403).json({ message: 'Not authorized to update this item' });
    }

    if (available == "available") {
      item.availability = true;
    } else if (available == "not available") {
      item.availability = false;
    } else {
      return res.status(400).json({ message: 'Invalid availability status' });
    }

    await item.save();

    res.status(200).json(item);
  } catch (error) {
    console.error('Availability update error:', error);
    res.status(500).json({
      message: 'Failed to update availability',
      error: error.message
    });
  }
};