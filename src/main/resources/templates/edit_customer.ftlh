<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
</head>

<body>
<header>
    <h1>Edit Customer</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/customers">Customers</a>
    </nav>
</header>

<main>
    <form action="/customers/${customer.id}/edit" method="post">
        <label><h3>First name:</h3> <input type="text" name="firstName" value="${customer.firstName}"
                                           required></label><br>
        <label><h3>Last name:</h3> <input type="text" name="lastName" value="${customer.lastName}" required></label><br>
        <label><h3>Email:</h3> <input type="text" name="email" value="${customer.email}" required></label><br>
        <label><h3>Phone:</h3> <input type="text" name="phoneNumber" value="${customer.phoneNumber}"></label><br>
        <label><h3>Address:</h3> <input type="text" name="address" value="${customer.address}"></label><br>
        <div style="display: flex; justify-content: space-between; margin-top: 20px;">
            <button type="submit" class="btn">Update Customer</button>
            <a href="/customers" class="btn">Back to Customers</a>
        </div>
    </form>
</main>
<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>
</body>
</html>
