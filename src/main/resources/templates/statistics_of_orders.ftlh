<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Statistics - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
    <style>
        .statistics-section {
            text-align: center;
            margin-bottom: 30px;
            color: #ff4081;
        }

        .statistics-section h2 {
            font-size: 2rem;
            margin-bottom: 10px;
            text-shadow: 0 0 10px rgba(255, 64, 129, 0.8);
        }

        .statistics-section p {
            font-size: 1.2rem;
            color: #f0f0f0;
            text-shadow: 0 0 5px rgba(255, 255, 255, 0.3);
        }

        .progress-container {
            margin-top: 20px;
            text-align: center;
        }

        .progress-bar {
            position: relative;
            background-color: #3a3a3a;
            height: 25px;
            border-radius: 15px;
            width: 100%;
            overflow: hidden;
            box-shadow: 0 3px 8px rgba(0, 0, 0, 0.4);
        }

        .progress-fill {
            background-color: #4caf50;
            height: 100%;
            border-radius: 15px;
            width: 100%;
        }

        .indicator {
            position: absolute;
            top: -7px;
            left: calc(${percentage}% - 3px);
            width: 6px;
            height: 40px;
            background-color: #ff4081;
            border-radius: 3px;
            box-shadow: 0 0 10px rgba(255, 64, 129, 1), 0 0 20px rgba(255, 64, 129, 1);
        }

        .label-container {
            display: flex;
            justify-content: space-between;
            margin-top: 5px;
            font-size: 1rem;
            font-weight: bold;
            color: #ccc;
        }
    </style>
</head>
<body>
<header>
    <h1>Order Statistics</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/products">Products</a>
        <a href="/customers">Customers</a>
    </nav>
</header>

<main>
    <div class="statistics-section">
        <h2>Order Prices</h2>
        <p><strong>Average Order Price:</strong> $${averageOrderPrice}</p>
        <p><strong>Maximum Order Price:</strong> $${maxOrderPrice}</p>
        <p><strong>Average Orders per Customer:</strong> ${averageOrdersPerCustomer}</p>
    </div>

    <div class="progress-container">
        <h2>Average Price Position Relative to Maximum</h2>
        <div class="progress-bar">
            <div class="progress-fill"></div>
            <div class="indicator"></div>
        </div>
        <div class="label-container">
            <span>0%</span>
            <span>${percentage}%</span>
            <span>100%</span>
        </div>
    </div>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store.</p>
</footer>

<button class="back-to-top" title="Back to top">↑</button>

<script>
    const backToTopBtn = document.querySelector(".back-to-top");
    window.addEventListener("scroll", () => {
        backToTopBtn.style.display = window.scrollY > 300 ? "flex" : "none";
    });

    backToTopBtn.addEventListener("click", () => {
        window.scrollTo({ top: 0, behavior: "smooth" });
    });
</script>
</body>
</html>
