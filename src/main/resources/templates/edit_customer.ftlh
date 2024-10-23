<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer - Auto Parts Store</title>
    <link rel="stylesheet" href="/static/styles/styles.css">
</head>

<body>
<header>
    <h1>Edit Customer</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/products">Products</a>
    </nav>
</header>

<main>
    <form action="/customer/${customer.id}/edit" method="post">
        <label><h3>First name:</h3> <input type="text" name="firstName" value="${customer.firstName}"
                                           required></label><br>
        <label><h3>Last name:</h3> <input type="text" name="firstName" value="${customer.lastName}"
                                          required></label><br>

        <label><h3>Email:</h3> <input type="text" name="email" value="${customer.email}" required></label><br>
        <label><h3>Phone:</h3> <input type="text" name="phoneNumber" value="${customer.phoneNumber}"></label><br>
        <label><h3>Address:</h3> <input type="text" name="address" value="${customer.address}"></label><br>
        <button type="submit" class="btn">Update Customer</button>
    </form>
    <a href="/products" class="btn">Back to Products</a>
</main>
<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>
</body>
</html>