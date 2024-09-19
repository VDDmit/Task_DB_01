<!DOCTYPE html>
<html>
<head>
    <title>CarFixPro</title>
</head>
<body>
<h1>CarFixPro</h1>
<hr>
<h4>Список всех товаров</h4>
<#list products as product>
    <div>
        <p><b>${product.product_name}</b> ${product.price} | <a href="/product/${product.product_id}">Подробнее...</a>
        </p>
    </div> </#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post">
    ID: <input type="text" name="product_id"/><br><br>
    Название товара: <input type="text" name="product_name"/><br><br>
    Описание товара: <input type="text" name="description"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Количество: <input type="number" name="stock_quantity"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
</body>
</html>