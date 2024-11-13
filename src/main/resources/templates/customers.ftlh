<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customers - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
</head>

<body>

<header>
    <h1>Our Customers</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/products">Products</a>
        <a href="/statistics_of_orders">Statistics of orders</a>
    </nav>
</header>

<main>
    <h2>Create New Customer</h2>
    <form action="/customers/create" method="post">
        <label>First Name: <input type="text" name="firstName" required></label><br>
        <label>Last Name: <input type="text" name="lastName" required></label><br>
        <label>Email: <input type="email" name="email" required></label><br>
        <label>Phone: <input type="text" name="phoneNumber"></label><br>
        <label>Address: <input type="text" name="address"></label><br>
        <button type="submit" class="btn">Create Customer</button>
    </form>

    <h2>Search Customers</h2>
    <form id="searchForm">
        <label>Search: <input type="text" id="searchInput" placeholder="Search by name, email, or phone"></label>
        <button type="button" class="btn" onclick="searchCustomers()">Search</button>
        <button type="button" class="btn" onclick="resetSearch()">Reset</button>
    </form>

    <h2>Customers List</h2>
    <div id="customersList">
        <#list customers as customer>
            <div class="customer-box" data-firstname="${customer.firstName}" data-lastname="${customer.lastName}"
                 data-email="${customer.email}" data-phone="${customer.phoneNumber}">
                <h3>${customer.firstName} ${customer.lastName}</h3>
                <p>Email: ${customer.email}</p>
                <p>Phone: ${customer.phoneNumber}</p>
                <p>Address: ${customer.address}</p>
                <div class="actions">
                    <form action="/customers/${customer.id}/delete" method="post" style="display: inline-block;"
                          onsubmit="return confirm('Are you sure you want to delete this customer with their orders?');">
                        <button type="submit" class="btn delete">Delete</button>
                    </form>
                    <a href="/customers/${customer.id}/edit" class="btn" style="text-decoration: none;">Edit</a>
                    <a href="/customers/${customer.id}/orders" class="btn" style="text-decoration: none;">Orders</a>
                </div>
            </div>
        </#list>
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

    function searchCustomers() {
        const searchTerm = document.getElementById('searchInput').value.toLowerCase();
        const customerBoxes = document.querySelectorAll('.customer-box');

        customerBoxes.forEach(box => {
            const firstName = box.getAttribute('data-firstname').toLowerCase();
            const lastName = box.getAttribute('data-lastname').toLowerCase();
            const email = box.getAttribute('data-email').toLowerCase();
            const phone = box.getAttribute('data-phone').toLowerCase();

            if (firstName.includes(searchTerm) || lastName.includes(searchTerm) ||
                email.includes(searchTerm) || phone.includes(searchTerm)) {
                box.style.display = 'block';
            } else {
                box.style.display = 'none';
            }
        });
    }

    function resetSearch() {
        document.getElementById('searchInput').value = '';
        const customerBoxes = document.querySelectorAll('.customer-box');
        customerBoxes.forEach(box => {
            box.style.display = 'block';
        });
    }
</script>

</body>

</html>
