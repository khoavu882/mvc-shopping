$(document).ready(function () {
    $("[data-js-form-slider]", function () {
        var el = $(this)
        var field = el.find('.field')
        var fill = el.find('.fill')
        var label = el.find('.-thumb')
        var min = field.attr('min') || 0
        var max = field.attr('max') || 100

        var value = field.val()
        var fillVal = 100 * (value - min) / (max - min)

        fill.css({"width": fillVal + "%"})
        label.find('.center').text(" " + value + " ")

        field.on('input', function () {
            var value = field.val()
            var fillVal = 100 * (value - min) / (max - min)

            fill.css({"width": fillVal + "%"})
            label.find('.center').text(" " + value + " ")
        })
    })
});

/* Check User Exist */

function checkExistUser() {
    var userName= $('#userName').val();
    $("#ajax-response-user").css("display", "block");

    $.ajax({
        type : "GET",
        url : "checkExistUser",
        data : {
            userName : userName
        },
        dataType : 'text',
        timeout : 100000,
        success : function(data) {
            if (data == "false") {
                $("#ajax-response-user").html("<i class=\"fa fa-check-circle\" style=\"font-size: 18px\" > Username hợp lệ</i>").css("color", "green");
                $('#submit').prop('disabled',false);
                $("#ajax-response-user").show(0).delay(3000).hide(0);
            } else {
                $("#ajax-response-user").html("<i class=\"fa fa-times-circle\" style=\"font-size: 18px\" > Username đã tồn tại! Vui lòng chọn tên khác.</i>").css("color", "red");
                $('#submit').prop('disabled',true);
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function checkExistProduct() {
    var name= $('#productName').val();
    $("#ajax-response-product").css("display", "block");

    $.ajax({
        type : "GET",
        url : "checkExistProduct",
        data : {
            productName : name
        },
        dataType : 'text',
        timeout : 100000,
        success : function(data) {
            if (data == "false") {
                $("#ajax-response-product").html("<i class=\"fa fa-check-circle\" style=\"font-size: 18px\" > Product Name </i>").css("color", "green");
                $('#submit').prop('disabled',false);
                $("#ajax-response-product").show(0).delay(3000).hide(0);
            } else {
                $("#ajax-response-product").html("<i class=\"fa fa-times-circle\" style=\"font-size: 18px\" > Product Name Exits! Please choose another.</i>").css("color", "red");
                $('#submit').prop('disabled',true);
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function checkOutOfStock() {
    var quantity = $('#quantity').val();
    var productId = $('#productId').val();
    $("#ajax-response-quantity").css("display", "block");
    console.log('aaa');
    $.ajax({
        type : "GET",
        url : "checkOutOfStock",
        data : {
            productId : productId,
            quantity : quantity
        },
        dataType : 'text',
        timeout : 100000,
        success : function(data) {
            console.log(data);
            if (data == "out") {
                $("#ajax-response-quantity").html("<i class=\"fa fa-check-circle\" style=\"font-size: 18px\" > Vượt quá đơn hàng cho phép</i>").css("color", "red");
                $('#regLink').addClass("disable");
                $("#ajax-response-quantity").show(0).delay(3000).hide(0);
            }
            if (data == "value"){
                $("#ajax-response-quantity").html("<i class=\"fa fa-times-circle\" style=\"font-size: 18px\" >Không cập nhật số lượng nhỏ hơn 0!</i>").css("color", "red");
                $('#regLink').addClass("disable");
            }
            if (data == "true") {
                $("#ajax-response-quantity").hidden;
                $('#regLink').removeClass("disable");
            }
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}
$('#regLink').on('click', function () {
    $('#regLink').attr('href', $('#regLink').attr('href') + $('input[name="quantity"]').val());
});

/* List Products */

// window.onscroll = function() {onTop()};
//
// function onTop() {
//     var x = document.getElementById("navbar-top");
//
//     if (document.body.scrollTop > 56 || document.documentElement.scrollTop > 56) {
//         x.style.display = "none";
//
//     } else {
//         x.style.display = "block";
//     }
// }

$('#list').on('click', function () {
    $('.item').removeClass('col-md-6').addClass('product-list');
});

$('#grid').on('click', function () {
    $('.item').removeClass('product-list').addClass('col-md-6');
});

/* Set rates + misc */
var taxRate = 0.05;
var shippingRate = 15.00;
var fadeTime = 300;


/* Assign actions */
$('.product-quantity input').change( function() {
    updateQuantity(this);
});

$('.product-removal button').click( function() {
    removeItem(this);
});


/* Recalculate cart */
function recalculateCart()
{
    var subtotal = 0;

    /* Sum up row totals */
    $('.product').each(function () {
        subtotal += parseFloat($(this).children('.product-line-price').text());
    });

    /* Calculate totals */
    var tax = subtotal * taxRate;
    var shipping = (subtotal > 0 ? shippingRate : 0);
    var total = subtotal + tax + shipping;

    /* Update totals display */
    $('.totals-value').fadeOut(fadeTime, function() {
        $('#cart-subtotal').html(subtotal.toFixed(2));
        $('#cart-tax').html(tax.toFixed(2));
        $('#cart-shipping').html(shipping.toFixed(2));
        $('#cart-total').html(total.toFixed(2));
        if(total == 0){
            $('.checkout').fadeOut(fadeTime);
        }else{
            $('.checkout').fadeIn(fadeTime);
        }
        $('.totals-value').fadeIn(fadeTime);
    });
}


/* Update quantity */
function updateQuantity(quantityInput)
{
    /* Calculate line price */
    var productRow = $(quantityInput).parent().parent();
    var price = parseFloat(productRow.children('.product-price').text());
    var quantity = $(quantityInput).val();
    var linePrice = price * quantity;

    /* Update line price display and recalc cart totals */
    productRow.children('.product-line-price').each(function () {
        $(this).fadeOut(fadeTime, function() {
            $(this).text(linePrice.toFixed(2));
            recalculateCart();
            $(this).fadeIn(fadeTime);
        });
    });
}

console.clear();

var image = document.querySelectorAll('.image')[0];
var zoom = document.querySelectorAll('.zoom')[0];
var zoomImage = document.querySelectorAll('.zoom-image')[0];

var clearSrc;


/* Remove item from cart */
function removeItem(removeButton)
{
    /* Remove row from DOM and recalc cart total */
    var productRow = $(removeButton).parent().parent();
    productRow.slideUp(fadeTime, function() {
        productRow.remove();
        recalculateCart();
    });
}