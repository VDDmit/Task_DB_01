<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders for ${customer.firstName} ${customer.lastName} - Auto Parts Store</title>
    <link rel="stylesheet" href="/styles/styles.css">
    <script>
        function formatDate(dateString) {
            const options = {year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit'};
            const date = new Date(dateString);
            return date.toLocaleDateString('en-US', options);
        }

        document.addEventListener('DOMContentLoaded', function () {
            const dateCells = document.querySelectorAll('tbody td:nth-child(2)');
            dateCells.forEach(cell => {
                cell.textContent = formatDate(cell.textContent);
            });
        });

    </script>
</head>
<body>

<header>
    <h1>Orders for ${customer.firstName} ${customer.lastName}</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/products">Products</a>
        <a href="/customers">Customers</a>
    </nav>
</header>

<main>
    <h2>Customer Information</h2>
    <p><strong>Name:</strong> ${customer.firstName} ${customer.lastName}</p>
    <p><strong>Email:</strong> ${customer.email}</p>
    <p><strong>Phone:</strong> ${customer.phoneNumber}</p>

    <h2>Orders List</h2>
    <table>
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr class="product-box">
                <td>${order.id}</td>
                <td>${order.orderDate}</td>
                <td>${order.totalAmount}</td>
                <div style="display: flex; gap: 10px;">
                    <td>
                        <a href="/customers/${customer.id}/order/${order.id}" class="btn">View</a>
                        <form action="/customers/${customer.id}/orders/${order.id}/delete" method="post" style="display:inline;"
                              onsubmit="return confirm('Are you sure you want to delete this order?');">
                            <button type="submit" class="btn delete">Delete</button>
                        </form>
                    </td>
                </div>
            </tr>
        </#list>
        </tbody>
    </table>

    <h3>Add New Order</h3>
    <form action="/order/create" method="post">
        <button type="submit" class="btn">Create Order</button>
    </form>


</main>


<footer>
    <p>&copy; 2024 Auto Parts Store</p>
</footer>

</body>
</html>
