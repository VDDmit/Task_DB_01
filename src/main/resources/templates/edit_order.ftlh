<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit ordered product - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
</head>

<body>
<header>
    <h1>Edit ordered product</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/products">Products</a>
        <a href="/customers">Customers</a>
    </nav>
</header>

<main>
    <form action="/customers/${customer.id}/order/${order.id}/${product.productId}/edit" method="post">
        <label for="unitPrice">Order Price:</label>
        <input type="number" step="0.01" id="unitPrice" name="unitPrice" value="${orderItem.unitPrice}" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${orderItem.quantity}" required>

        <div style="display: flex; justify-content: space-between; margin-top: 20px;">
            <button type="submit" class="btn edit">Save</button>
            <a href="/customers/${customer.id}/order/${order.id}" class="btn" >Back to products for order</a>
        </div>
    </form>
</main>
</body>
</html>
