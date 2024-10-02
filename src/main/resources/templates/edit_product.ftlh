<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product - Auto Parts Store</title>
    <link rel="stylesheet" href="/static/styles.css">
    <style>
        /* Basic Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f1f5f9;
            color: #333;
            line-height: 1.6;
        }

        /* Header and Navigation */
        header {
            background: linear-gradient(135deg, #0f4c81, #0088cc);
            color: white;
            padding: 20px 0;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 2.5rem;
            font-weight: 700;
        }

        nav {
            margin-top: 15px;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-size: 1.1rem;
            margin: 0 15px;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background 0.3s ease;
        }

        nav a:hover {
            background: rgba(255, 255, 255, 0.2);
        }

        /* Main Section */
        main {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
        }

        h3 {
            margin-bottom: 10px;
            color: #007bff;
        }

        form input[type="text"],
        form input[type="number"],
        form textarea {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 15px;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        form input[type="text"]:focus,
        form input[type="number"]:focus,
        form textarea:focus {
            border-color: #007bff;
            outline: none;
        }

        .btn {
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 1rem;
            transition: background-color 0.3s ease, transform 0.2s;
            display: inline-block;
            margin-top: 10px;
        }

        .btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        /* Footer */
        footer {
            background-color: #0f4c81;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: 40px;
        }

        footer p {
            margin: 0;
            font-size: 1rem;
        }
    </style>
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
        <label><h3>Product Name:</h3> <input type="text" name="productName" value="${product.productName}" required></label><br>
        <label><h3>Description:</h3> <textarea name="description">${product.description}</textarea></label><br>
        <label><h3>Price:</h3> <input type="number" step="0.01" name="price" value="${product.price}" required></label><br>
        <label><h3>Stock Quantity:</h3> <input type="number" name="stockQuantity" value="${product.stockQuantity}" required></label><br>
        <button type="submit" class="btn">Update Product</button>
    </form>
    <a href="/products" class="btn">Back to Products</a>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>
</body>
</html>
