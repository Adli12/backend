import express from "express";
import mongoose from "mongoose";
import cors from "cors";
import UserRoute from "./routes/UserRoute.js";

const app = express();
mongoose
  .connect("mongodb://localhost:27017/testingappdb")
  .then(() => {
    console.log("connect to mongodb");
  })
  .catch((error) => {
    console.log("failed connect to server");
  });

//   middleware
app.use(cors());
app.use(express.json());
app.use(UserRoute);

app.listen(5000, () => console.log("Server Up and running"));
