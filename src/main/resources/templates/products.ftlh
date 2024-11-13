<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
    <style>
        #productsTable th:nth-child(2),
        #productsTable td:nth-child(2) {
            white-space: normal;
            word-wrap: break-word;
        }
        textarea[name="description"] {
            width: 100%;
            height: 100px;
            resize: none;
            box-sizing: border-box;
        }
    </style>

</head>

<body>

<header>
    <h1>Our Products</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/customers">Customers</a>
        <a href="/statistics_of_orders">Statistics of orders</a>
    </nav>
</header>

<main>
    <form action="/products" method="get" style="display: flex; gap: 10px;">
        <label>
            <input type="text" name="product_name" placeholder="Search by name">
        </label>
        <button type="submit" class="btn">Search</button>
        <a href="/products" class="btn" style="text-decoration: none; align-content: center;">Reset</a>
    </form>

    <h2>Create New Product</h2>
    <form action="/product/create" method="post">
        <label>Product Name: <input type="text" name="productName" required></label><br>
        <label>Description: <textarea name="description"></textarea></label><br>
        <label>Price: <input type="number" step="0.01" name="price" min="0" required></label><br>
        <label>Stock Quantity: <input type="number" name="stockQuantity" min="1" required></label><br>
        <button type="submit" class="btn">Create Product</button>
    </form>

    <h2>Products List</h2>

    <div id="productsContainer">
        <table id="productsTable">
            <thead>
            <tr>
                <th class="header">Product Name</th>
                <th class="header">Description</th>
                <#assign newOrder = (order == "asc")?then("desc", "asc")>
                <th class="header">
                    <a href="?sort=price&order=${newOrder}&page=${currentPage}&product_name=${product_name!''}">Price</a>
                </th>
                <th class="header">
                    <a href="?sort=stockQuantity&order=${newOrder}&page=${currentPage}&product_name=${product_name!''}">Stock</a>
                </th>
                <th class="header" style="margin-right: 90px ">Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list products as product>
                <tr class="product-box">
                    <td>${product.productName}</td>
                    <td>${product.description}</td>
                    <td class="center">${product.price}</td>
                    <td class="center">${product.stockQuantity}</td>
                    <td class="actions">
                        <div style="display: flex; gap: 10px;">
                            <form action="/product/${product.productId}/delete" method="post"
                                  style="display:inline-block;"
                                  onsubmit="return confirm('Are you sure you want to delete this product?');">
                                <button type="submit" class="btn delete">Delete</button>
                            </form>
                            <a href="/product/${product.productId}/edit" class="btn"
                               style="text-decoration: none;">Edit</a>
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <!-- Пагинация -->
        <nav aria-label="Page navigation" style="display: flex; justify-content: center; margin-top: 20px;">
            <ul class="pagination" style="display: flex; list-style: none; padding: 0;">
                <li class="page-item" style="margin-right: 8px;">
                    <a class="page-link" href="?page=0&product_name=${product_name!''}">First</a>
                </li>

                <#if currentPage == 0>
                    <li class="page-item disabled" style="margin-right: 8px;">
                        <span class="page-link">Previous</span>
                    </li>
                <#else>
                    <li class="page-item" style="margin-right: 8px;">
                        <a class="page-link"
                           href="?page=${currentPage - 1}&product_name=${product_name!''}">Previous</a>
                    </li>
                </#if>

                <li class="page-item disabled" style="margin-right: 8px;">
                    <span class="page-link">Page ${currentPage + 1} of ${totalPages}</span>
                </li>

                <#if currentPage == (totalPages - 1)>
                    <li class="page-item disabled" style="margin-right: 8px;">
                        <span class="page-link">Next</span>
                    </li>
                <#else>
                    <li class="page-item" style="margin-right: 8px;">
                        <a class="page-link" href="?page=${currentPage + 1}&product_name=${product_name!''}">Next</a>
                    </li>
                </#if>

                <li class="page-item">
                    <a class="page-link" href="?page=${totalPages - 1}&product_name=${product_name!''}">Last</a>
                </li>
            </ul>
        </nav>

    </div>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>

<button id="backToTopBtn" class="back-to-top">&#8679;</button>

<script>
    const backToTopBtn = document.getElementById("backToTopBtn");

    window.onscroll = function () {
        if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
            backToTopBtn.style.display = "flex";
        } else {
            backToTopBtn.style.display = "none";
        }
    };

    backToTopBtn.onclick = function () {
        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    };

</script>

</body>

</html>
