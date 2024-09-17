<!DOCTYPE html>
<html>

<head>
    <title>test</title>

</head>

<body>

<?php
if($_POST["login"]) {
    ?>
    <h3>your data</h3>
    <div>
        <label for="login">login:</label>
        <span id="login"><?php print(strip_tags($_POST["login"]));?>
    </div>
    <div>
        <label for="password">password:</label>
        <span id="password"><?php print(strip_tags($_POST["password"]));?>
    </div>
    <?php
} else {
    ?>
    <h3>please login</h3>
    <form id="login_form" name="login_form" action="test.php" method="post">
        <div>
            <label for="login">login</label>
            <span id="login"><input type="text" id="login" name="login" />
        </div>
        <div>
            <label for="password">password</label>
            <span id="password"><input type="password" id="password" name="password" />
        </div>
        <div>
            <input type="submit" name="submit" />
        </div>
    </form>


    <?php
}
?>



</body>
</html>
