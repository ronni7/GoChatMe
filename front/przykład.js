function loadMap() {
	
	var jObj;
	var request = new XMLHttpRequest();
	url = "localhost:8080/login";
	request.open("POST", url);
	//dla POST nalezy dodac obiekt do parametrów zapytania w funkcji send
	//np. request.send('login=imie&password=TajneHaslo1');
	//	, dla GET mozna dokleic do url-a
//	request.setRequestHeader('Content-type', 'application/json'); to moze sie przydac
	request.send('login=imie&password=TajneHaslo1');
	request.onload = function() {
		if (this.readyState === 4 && this.status === 200) {
			jObj = JSON.parse(request.responseText);
				for (var i = 0; i < jObj.length; i++) { //jesli jest wiecej niz jeden obiekt iteruj po tablicy			
					//przykład jak wyciagnąc wartosci i zapisac je w zmiennej typu Object
					var info = {
						ID : jObj[i].id,
						Imie: jObj[i].name
						//etc					
					}
					alert(info);
			
									
				}
		} else if (this.status === 400) {
			alert("blad serwera/url");
		} else {
			alert(this.status);
		}
		
	}
}