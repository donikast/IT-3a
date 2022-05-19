function editProfileInfo() {
const postedData = toJSONString(document.querySelector('form.user-info'));

fetch('updateInfo', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: postedData
})
.then((response) => response.json())
.then((data) => {
  document.getElementById("message").innerHTML=data.result;
})
.catch((error) => {
  console.error('Error:', error);
});
}


  function toJSONString(form) {
	let obj = {};
	let elements = form.querySelectorAll( "input" );
	for(let i = 0; i < elements.length; i++) {
		let element = elements[i];
		let name = element.name;
		let value = element.value;

		if(name) {
			obj[name] = value;
		}
	}

	return JSON.stringify(obj);
}