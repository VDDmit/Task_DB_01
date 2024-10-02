<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auto Parts Store</title>
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
    <h1>Welcome to Auto Parts Store</h1>
    <nav>
        <a href="/products">Products</a>
        <a href="/customers">Customers</a>
        <a href="/orders">Orders</a>
    </nav>
</header>

<main>
    <h2>Discover a wide range of auto parts at competitive prices!</h2>
    <p>Your one-stop shop for all your automotive needs. Explore our product catalog to find the best parts for your vehicle.</p>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>
</body>
</html>
