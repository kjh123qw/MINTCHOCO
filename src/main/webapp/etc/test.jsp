<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> DIV TEST </title>

<style>
    *{
        margin: 0;
        padding: 0;
        margin: 0 auto;
        box-sizing: border-box;
    }
    #bg{
        max-width: 1200px;
        margin: auto;
        background-color: aqua;
    }
    .item{
        width: 25%;
        float: left;
        border: 1px solid black;
    }
    .poster{
        height: 400px;
        background-color: aquamarine;
    }

    .poster img{
        width: 100%;
        height: 400px;
    }

    .title{
        height: 100px;
        background-color: burlywood;
    }
</style>

</head>
<body>

    <div id="bg">

        <div class="item">
            <div class="poster">
                <img src="../DEV/image0.png" alt="">
            </div>
            <div class="title">
            </div>
        </div>

        <div class="item">
            <div class="poster">
            </div>
            <div class="title">
            </div>
        </div>

        <div class="item">
            <div class="poster">
            </div>
            <div class="title">
            </div>
        </div>

        <div class="item">
            <div class="poster">
            </div>
            <div class="title">
            </div>
        </div>

    </div>

</body>
</html>