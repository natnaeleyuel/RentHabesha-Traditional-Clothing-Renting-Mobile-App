import express from 'express';
import { auth } from '../middlewares/auth.js';
import { singleUpload } from '../utils/upload.js';
import {
  addClothingItem,
  getAllClothingItems,
  getClothingItemById,
  updateClothingItem,
  deleteClothingItem,
  updateItemAvailability
} from '../controllers/clothing.controller.js';

const router = express.Router();

router.use(auth);

router.post('/', auth, singleUpload, addClothingItem);
router.get('/', getAllClothingItems);
router.get('/:id', getClothingItemById);
router.patch('/:id', auth, singleUpload, updateClothingItem);
router.delete('/:id', auth, deleteClothingItem);
router.patch('/:id/availability', auth, updateItemAvailability);

export default router;