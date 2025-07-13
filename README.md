## 📦 Cloudinary Boot

**Cloudinary Boot** is a Spring Boot application that integrates with [Cloudinary](https://cloudinary.com/) to manage media files. It allows you to **upload**, **update (overwrite)**, and **delete** images using the Cloudinary Java SDK.

---

## 🚀 Features

- ✅ Upload images to Cloudinary folders
- 🔄 Overwrite/update existing images
- 🗑️ Delete images by `public_id`
- ☁️ Secure image URLs via HTTPS
- 📁 Organized storage using folder paths

---

## 🔧 Technologies Used

- Java 21
- Spring Boot
- Cloudinary Java SDK
- Maven

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/HlyamHtetKyaw/cloudinary-boot.git
cd cloudinary-boot
```

### 2. Add Cloudinary Credentials

You need to create   `.env` file and add your Cloudinary credentials as environment variables.

### 🔐 File example `.env`

```
CLOUDINARY_CLOUD_NAME={your_cloud_name}
CLOUDINARY_API_KEY={your_api_key}
CLOUDINARY_API_SECRET={your_api_secret}
```

---

## 🧪 Testing

You can test the endpoints using:

- **Postman**
- **cURL**

---
