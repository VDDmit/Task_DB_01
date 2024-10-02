<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products - Auto Parts Store</title>
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
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
            transition: all 0.3s ease;
        }

        main:hover {
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
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
        }

        .btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        .btn.delete {
            background-color: #dc3545;
        }

        .btn.delete:hover {
            background-color: #c82333;
        }

        h2 {
            color: #007bff;
            font-size: 1.75rem;
            margin-bottom: 20px;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            position: relative;
        }

        h2::before {
            content: "";
            position: absolute;
            bottom: -3px;
            left: 0;
            width: 80px;
            height: 4px;
            background-color: #007bff;
        }

        /* Table Styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
            background: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        th,
        td {
            padding: 15px;
            border-bottom: 1px solid #e5e5e5;
            text-align: left;
            font-size: 1rem;
        }

        th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            cursor: pointer; /* Pointer cursor for sortable columns */
            position: relative; /* To position the arrows */
        }

        th .sort-arrow {
            margin-left: 8px;
            font-size: 0.75rem;
            transition: transform 0.3s ease;
        }

        td {
            background-color: #f9f9f9;
        }

        tr:nth-child(even) td {
            background-color: #f1f5f9;
        }

        tr:hover td {
            background-color: #f0f0f5;
        }

        .actions {
            text-align: center;
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

        /* Back to Top Button */
        .back-to-top {
            position: fixed;
            bottom: 30px;
            right: 30px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            font-size: 24px;
            display: none;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s;
        }

        .back-to-top:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            main {
                padding: 15px;
            }

            nav {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            nav a {
                margin-bottom: 10px;
            }

            table,
            th,
            td {
                font-size: 0.9rem;
            }

            .back-to-top {
                bottom: 20px;
                right: 20px;
                width: 40px;
                height: 40px;
                font-size: 20px;
            }
        }
    </style>
</head>

<body>

<header>
    <h1>Our Products</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/customers">Customers</a>
        <a href="/orders">Orders</a>
    </nav>
</header>

<main>
    <form action="/products" method="get" style="display: flex; gap: 10px;">
        <label>
            <input type="text" name="product_name" placeholder="Search by name">
        </label>
        <button type="submit" class="btn">Search</button>
        <!-- New button to reset search -->
        <a href="/products" class="btn" style="text-decoration: none;">Reset</a>
    </form>


    <h2>Create New Product</h2>
    <form action="/product/create" method="post">
        <label>Product Name: <input type="text" name="productName" required></label><br>
        <label>Description: <textarea name="description"></textarea></label><br>
        <label>Price: <input type="number" step="0.01" name="price" required></label><br>
        <label>Stock Quantity: <input type="number" name="stockQuantity" required></label><br>
        <button type="submit" class="btn">Create Product</button>
    </form>

    <h2>Products List</h2>
    <table id="productsTable">
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Description</th>
            <th id="priceHeader">Price<span class="sort-arrow" id="priceArrow">&#8597;</span></th>
            <th id="stockHeader">Stock<span class="sort-arrow" id="stockArrow">&#8597;</span></th>
            <th class="actions">Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.productName}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.stockQuantity}</td>
                <td class="actions">
                    <form action="/product/${product.productId}/delete" method="post" style="display:inline-block;"
                          onsubmit="return confirm('Are you sure you want to delete this product?');">
                        <button type="submit" class="btn delete">Delete</button>
                    </form>
                    <a href="/product/${product.productId}/edit" class="btn">Edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>

<!-- Back to Top Button -->
<button id="backToTopBtn" class="back-to-top">&#8679;</button>

<script>
    // Sort table function
    function sortTable(table, col, asc) {
        const dirModifier = asc ? 1 : -1;
        const rows = Array.from(table.querySelectorAll('tr:nth-child(n+2)')); // Exclude header row
        const sortedRows = rows.sort((a, b) => {
            const aColText = parseFloat(a.querySelectorAll('td')[col].innerText) || a.querySelectorAll('td')[col].innerText;
            const bColText = parseFloat(b.querySelectorAll('td')[col].innerText) || b.querySelectorAll('td')[col].innerText;

            return (aColText > bColText ? 1 : -1) * dirModifier;
        });

        // Remove all existing rows
        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }

        // Append the sorted rows
        table.append(...sortedRows);
    }

    // Event listeners for sorting
    document.getElementById("priceHeader").addEventListener("click", () => {
        const table = document.getElementById("productsTable");
        const isAscending = table.getAttribute('data-order') === 'asc';
        sortTable(table.querySelector('tbody'), 2, !isAscending);
        table.setAttribute('data-order', isAscending ? 'desc' : 'asc');

        // Update arrow direction
        document.getElementById("priceArrow").innerHTML = isAscending ? '&#8595;' : '&#8593;'; // Down or up arrow
        document.getElementById("stockArrow").innerHTML = '&#8597;'; // Reset other arrow
    });

    document.getElementById("stockHeader").addEventListener("click", () => {
        const table = document.getElementById("productsTable");
        const isAscending = table.getAttribute('data-order') === 'asc';
        sortTable(table.querySelector('tbody'), 3, !isAscending);
        table.setAttribute('data-order', isAscending ? 'desc' : 'asc');

        // Update arrow direction
        document.getElementById("stockArrow").innerHTML = isAscending ? '&#8595;' : '&#8593;'; // Down or up arrow
        document.getElementById("priceArrow").innerHTML = '&#8597;'; // Reset other arrow
    });

    // Get the button
    const backToTopBtn = document.getElementById("backToTopBtn");

    // Show or hide the button based on scroll position
    window.onscroll = function () {
        if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
            backToTopBtn.style.display = "flex";
        } else {
            backToTopBtn.style.display = "none";
        }
    };

    // Scroll to top when the button is clicked
    backToTopBtn.onclick = function () {
        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    };
</script>

</body>

</html>
