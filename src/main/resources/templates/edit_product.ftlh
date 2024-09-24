<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product - Auto Parts Store</title>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
<header>
    <h1>Edit Product</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/customers">Customers</a>
        <a href="/orders">Orders</a>
    </nav>
</header>

<main>
    <form action="/product/${product.productId}/edit" method="post">
        <label>Product Name: <input type="text" name="productName" value="${product.productName}" required></label><br>
        <label>Description: <textarea name="description">${product.description}</textarea></label><br>
        <label>Price: <input type="number" step="0.01" name="price" value="${product.price}" required></label><br>
        <label>Stock Quantity: <input type="number" name="stockQuantity" value="${product.stockQuantity}" required></label><br>
        <button type="submit" class="btn">Update Product</button>
    </form>
    <a href="/products" class="btn">Back to Products</a>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>
</body>
</html>
