<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products for order with id: ${order.id}</title>
    <link rel="stylesheet" href="/styles/styles.css">
</head>

<body>
<header>
    <h1>Products for order with id: ${order.id}</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/customers/${customer.id}/orders">Back to customer orders</a>
        <a href="/products">Products</a>
        <a href="/customers">Customers</a>
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
                        <form action="/orders/${order.id}/products/${item.product.productId}/edit" method="get" style="display:inline;">
                            <button type="submit" class="btn edit">Edit</button>
                        </form>

                        <form action="/customers/${customer.id}/order/${order.id}/products/${item.product.productId}/delete" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to remove this product?');">
                            <input type="hidden" name="quantity" value="${item.quantity}">
                            <button type="submit" class="btn delete">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</main>

<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>

</body>
</html>
