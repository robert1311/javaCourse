$(document).ready(function(){
	loadInventory();
	$('#itemNum').hide();
	$('#change-button').hide();
	
	
	//var currentAmount = parseFloat($('#total-in').val());
	
	$('#dollar-button').click(function (event){	
		clearChange();
		$('#change-button').show();
		var currentAmount = parseFloat($('#total-in').val());
		currentAmount += parseFloat(1.00);
		$('#total-in').val(currentAmount.toFixed(2));
	});
	
	$('#quarter-button').click(function (event){
		var currentAmount = parseFloat($('#total-in').val());
		clearChange();
		$('#change-button').show();
		currentAmount += parseFloat(0.25);
		$('#total-in').val(currentAmount.toFixed(2));
	});
	
	$('#dime-button').click(function (event){
		var currentAmount = parseFloat($('#total-in').val());
		clearChange();
		$('#change-button').show();
		currentAmount += parseFloat(0.10);
		$('#total-in').val(currentAmount.toFixed(2));
	});
	
	$('#nickel-button').click(function (event){
		var currentAmount = parseFloat($('#total-in').val());
		clearChange();
		$('#change-button').show();
		currentAmount += parseFloat(0.05);
		$('#total-in').val(currentAmount.toFixed(2));
	});
	
	
	$('#change-button').click(function(event){
	var total = $('#total-in').val();
	var pennies = parseInt(total*100);
	var dimes = 0;
	var nickels = 0;
	var quarters = 0;
	
	while (pennies >= 25){
		quarters = quarters + 1;
		pennies -= 25;
	}
	while (pennies >= 10){
		dimes = dimes + 1;
		pennies -= 10;
	}
	while (pennies >= 5){
		nickels = nickels + 1;
		pennies -= 5;
	}
	
	var coins = ''
	if(quarters > 0){
	coins +=' '+ quarters + ' Quarters,';
	}
	if(dimes > 0){
	coins += ' '+ dimes +' Dimes,';	
	}
	if(nickels > 0){
		coins += ' '+ nickels +' Nickels,';
	}
	if(pennies > 0){
		coins += ' '+ pennies +' Pennies,';
	}
	
	$('#change').val(coins);
	
	
	clearMessages();
	$('#total-in').val('0.00');
	$('#change-button').hide();
		
	});
	
	
	
	$('#purchase-button').click(function(event){
		
		clearChange();
		$('#message').val('');
		var change= $('#change');
		var id = $('#itemNum').val();
		var currentAmount = parseFloat($('#total-in').val());
		
		$.ajax({
			type: 'POST',
			url: 'http://tsg-vending.herokuapp.com/money/'+ currentAmount.toFixed(2) +'/item/'+ id ,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			'datatype': 'json',
			success: function(data, status){
				$('#errorMessages').empty();
				var numQuarters = data.quarters;
				var numDimes = data.dimes;
				var numNickels = data.nickels;
				var numPennies = data.pennies;
				
				var coins = '';
				if(numQuarters > 0){
				coins +=' '+ numQuarters + ' Quarters,';
				}
				if(numDimes > 0){
				coins += ' '+ numDimes +' Dimes,';	
				}
				if(numNickels > 0){
					coins += ' '+ numNickels +' Nickels,';
				}
				if(numPennies > 0){
					coins += ' '+ numPennies +' Pennies,';
				}
				
				change.val(coins);
				$('#change-button').hide();
				$('#total-in').val('0.00');
				$('#message').val('Thank You!!!');
				$('#item').val('');
				$('#itemNum').val('');
				
				loadInventory();
				
				
			},
			error: function(jqXHR,error, errorThrown){
				jsonValue = jQuery.parseJSON( jqXHR.responseText );
			$('#message').val(''+ jsonValue.message +'');
			}
		});
		
	});
	
});


//helper functions

function loadInventory(){
	clearInventory();
	
	
	
	var inventory = $('#inventory');
	var itemIndex = 1;
	
	$.ajax({
		type: 'GET',
		url: 'http://tsg-vending.herokuapp.com/items',
		success: function(itemArray){
			$.each(itemArray, function(index, item){
				var itemId = item.id;
				var name = item.name;
				var price = item.price;
				var quantity = item.quantity;
				
				
				var block = '<div class="col-sm-4">';
					block += '<a onclick = "selectedItem('+ itemId +','+itemIndex+')"><div class="selections">';
					block += '<h4  class="itemIds">' + itemIndex +'</h4>';
					block += '<h4  class= "names">'+ name +'</h4>';
					block += '<h4  class="prices">$ '+ price +'</h4>';
					block += '<h4  class="quantities">quantity left: '+ quantity +'</h4>';
					block += '</div></a>';
					block += '</div>';
				
					inventory.append(block);
					
					itemIndex++;
				
			});
		},
		error: function(jqXHR,error, errorThrown){
			
				jsonValue = jQuery.parseJSON( jqXHR.responseText );
			$('#message').val(''+ jsonValue.message +'');
			}
	});
	
	
};

function clearInventory(){
	
	$('#inventory').empty();
};

function selectedItem(itemId, itemIndex){
	clearChange();
	clearMessages();
	$('#item').val('');
	$('#item').val(itemIndex);
	$('#itemNum').val(itemId);
}

function clearMessages(){
	$('#message').val('');
	$('#item').val('');
}

function clearChange(){
	$('#change').val('');
}