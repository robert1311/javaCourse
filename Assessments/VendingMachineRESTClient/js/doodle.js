<div id="item1" class="col-sm-4">
	<a><div id="box1" class="selections">
	
	<p>item1</p>
	
	</div></a>
</div>


margin-top: 5px;
	margin-bottom: 5px;
	padding-bottom: 70px;
	padding-top: 70px;
				
<div id="item1" class="col-sm-4">
	<a class= "blocks"><div id="box1" class="selections">
		<h4 id="itemId" class="itemIds">1</h4>
		<h4 id="name" class= "names">Item name</h4>
		<h4 id="price" class="prices">price</h4>
		<h4 id="quantity" class="quantities">quantity left: #</h4>
	</div></a>
</div>				


<div id="item1" class="col-sm-4">
	<a><div class="selections">
		<h4  class="itemIds">1</h4>
		<h4  class= "names">Item name</h4>
		<h4  class="prices">price</h4>
		<h4  class="quantities">quantity left: #</h4>
	</div></a>
</div>

  BigDecimal changeDue = currentAmt.subtract(dao.getItem(selection).getCost());
        int numPennies = Integer.parseInt(changeDue.toString().replace(".", ""));

        int numQuarters = Coin.QUARTER.toDenomination(numPennies);

        numPennies -= numQuarters * Coin.QUARTER.denomValue();
        int numDimes = Coin.DIME.toDenomination(numPennies);

        numPennies -= numDimes * Coin.DIME.denomValue();
        int numNickels = Coin.NICKEL.toDenomination(numPennies);

        numPennies -= numNickels * Coin.NICKEL.denomValue();

        int[] change = {
            numQuarters, numDimes, numNickels, numPennies};
        return change;