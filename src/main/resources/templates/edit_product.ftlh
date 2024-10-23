<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product - Auto Parts Store</title>
    <link rel="stylesheet" href="/static/styles/styles.css">
</head>

<body>
<header>
    <h1>Edit Product</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/customers">Customers</a>
    </nav>
</header>

<main>
    <form action="/product/${product.productId}/edit" method="post">
        <label><h3>Product Name:</h3> <input type="text" name="productName" value="${product.productName}"
                                             required></label><br>
        <label><h3>Description:</h3> <textarea name="description">${product.description}</textarea></label><br>
        <label><h3>Price:</h3> <input type="number" step="0.01" name="price" value="${product.price}"
                                      required></label><br>
        <label><h3>Stock Quantity:</h3> <input type="number" name="stockQuantity" value="${product.stockQuantity}"
                                               required></label><br>
        <button type="submit" class="btn">Update Product</button>
    </form>
    <a href="/products" class="btn">Back to Products</a>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>
</body>
</html>
