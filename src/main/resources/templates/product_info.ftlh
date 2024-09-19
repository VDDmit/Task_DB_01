<!DOCTYPE html>
<html>
<head>
    <title>CarFixPro</title>
</head>
<body>
<h1>CarFixPro</h1>
<hr>
<h4>Подробная информация о товаре</h4>
<b>Название товара: </b>${product.product_name}<br>
<b>Описание товара: </b>${product.description}<br>
<b>Цена: </b>${product.price}<br>
<b>Количество: </b>${product.stock_quantity}<br>


<hr>
<h3>Удалить товар</h3>
<form action="/product/delete/${product.product_id}" method="post">
    <input type="submit" value="Удалить товар"/>
</form>
</body>
</html>