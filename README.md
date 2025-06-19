# 🛒 Android Store App

An Android application built using **Kotlin**, implementing **MVVM architecture**, **Retrofit**, and **Coil** to display products from the [Fake Store API](https://fakestoreapi.com/products). The app includes a splash screen, paginated product list, real-time search, and detailed product view.

---

## 🚀 Features

- ✅ Splash Screen
- ✅ MVVM Architecture
- ✅ API Integration with Retrofit
- ✅ Coil for image loading
- ✅ Product List Screen using RecyclerView
- ✅ Product Detail Screen
- ✅ Real-time Search Functionality
- ✅ Pagination (infinite scroll)
- ✅ Loading Indicator during data fetch
- ✅ Error Handling for network/API failures

---

## 📦 API

- **Base URL:** `https://fakestoreapi.com`
- **Endpoint Used:** `/products`

---

## 🧱 Tech Stack

| Layer        | Technology       |
|--------------|------------------|
| Language     | Kotlin           |
| Architecture | MVVM             |
| Networking   | Retrofit2        |
| Image Loading| Coil             |
| UI           | XML Layouts      |
| UI Logic     | RecyclerView     |
| Binding      | ViewBinding      |
| Others       | LiveData, ViewModel, Handler |

---

## 🧑‍🎨 Screens in the App

### 1. **Splash Screen**
- Shown on app launch for branding and loading initialization.

### 2. **Product List Screen**
- Displays all products using RecyclerView.
- Implements pagination: loads more products when user scrolls to bottom.
- Shows product name, price, image, and short description.

### 3. **Product Detail Screen**
- Launched on item click.
- Displays full details including image, title, price, description, and category.

---

## 🔧 How to Run the Project
![splash](https://github.com/user-attachments/assets/fe5c1b96-65b1-4146-968b-b7f8cefedce8)

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Mrjemini/Store-App.git
