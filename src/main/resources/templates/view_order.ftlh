<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products for order with id: ${order.id} - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
    <style>
        .product-scroll-box {
            max-height: 150px;
            overflow-y: scroll;
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .product-scroll-box label {
            display: block;
            margin-bottom: 5px;
        }

        .quantity-inputs {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .quantity-inputs input {
            width: 70px;
            padding: 5px;
        }

        .search-input {
            margin-bottom: 10px;
            padding: 10px;
            width: 100%;
            box-sizing: border-box;
            background-color: #333;
            color: #fff;
            border: 1px solid #555;
            border-radius: 4px;
        }

        .search-input::placeholder {
            color: #bbb;
        }
    </style>
</head>

<body>
<header>
    <h1>Products for order with id: ${order.id}</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/products">Products</a>
        <a href="/customers">Customers</a>
        <a href="/customers/${customer.id}/orders">Back to customer orders</a>
    </nav>
</header>

<main>
    <h2>Customer Information</h2>
    <p><strong>Name:</strong> ${customer.firstName} ${customer.lastName}</p>
    <p><strong>Email:</strong> ${customer.email}</p>
    <p><strong>Phone:</strong> ${customer.phoneNumber}</p>

    <h2>Products List</h2>

    <table>
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Order Price</th>
            <th>Quantity</th>
            <th>Final Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list orderItems as item>
            <tr>
                <td>${item.product.productName}</td>
                <td>${item.product.price}</td>
                <td>${item.unitPrice}</td>
                <td>${item.quantity}</td>
                <td>${item.unitPrice * item.quantity}</td>
                <td>
                    <div style="display: flex; gap: 10px;">
                        <form action="/customers/${customer.id}/order/${order.id}/${item.product.productId}/edit"
                              method="get" style="display:inline;">
                            <button type="submit" class="btn edit">Edit</button>
                        </form>

                        <form action="/customers/${customer.id}/order/${order.id}/products/${item.product.productId}/delete"
                              method="post" style="display:inline;"
                              onsubmit="return confirm('Are you sure you want to remove this product?');">
                            <input type="hidden" name="quantity" value="${item.quantity}">
                            <button type="submit" class="btn delete">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <input type="text" id="searchInput" class="search-input" placeholder="Search products..."
           onkeyup="filterProducts()">

    <div class="product-scroll-box" id="productList">
        <#list products as product>
            <label>
                <input type="radio" name="selectedProduct" value="${product.productId}">
                ${product.productName} - $${product.price} (In stock: ${product.stockQuantity})
            </label>
        </#list>
    </div>

    <div class="quantity-inputs">
        <label>Quantity:
            <input type="number" name="quantity" id="quantityInput" min="1" required>
        </label>
    </div>

    <form id="addProductForm" action="/customers/${customer.id}/order/${order.id}/add_product_for_order" method="post">
        <input type="hidden" name="productId" id="productId">
        <input type="hidden" name="quantity" id="quantity">
        <button type="submit" class="btn add">Add product</button>
    </form>
</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>

<script>
    document.getElementById('addProductForm').onsubmit = function (event) {
        const selectedProduct = document.querySelector('input[name="selectedProduct"]:checked');
        const quantityInput = document.getElementById('quantityInput');

        if (!selectedProduct || !quantityInput.value || quantityInput.value <= 0) {
            alert("Please select a product and indicate quantity.");
            event.preventDefault();
            return;
        }
        document.getElementById('productId').value = selectedProduct.value;
        document.getElementById('quantity').value = quantityInput.value;
    };

    function filterProducts() {
        const searchInput = document.getElementById('searchInput');
        const filter = searchInput.value.toLowerCase();
        const productList = document.getElementById('productList');
        const labels = productList.getElementsByTagName('label');

        for (let i = 0; i < labels.length; i++) {
            const productName = labels[i].textContent || labels[i].innerText;
            if (productName.toLowerCase().indexOf(filter) > -1) {
                labels[i].style.display = "";
            } else {
                labels[i].style.display = "none";
            }
        }
    }
</script>

</body>
</html>
