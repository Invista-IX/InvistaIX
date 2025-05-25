
const selectProprietario = document.getElementById("proprietarioImovelSelectInput");
const divProprietario = document.getElementById("cadastroProprietario");
const hidden = document.querySelectorAll(".toggleProprietario");

const proprietarioImovel = document.getElementById("propritarioImovelInput");
const proprietarioCPFCPNJ = document.getElementById("cpfCpnjProprietarioInput");
const proprietaioTelefone = document.getElementById("")

const proprietarioCPFJNPJLabel = document.getElementById("labelCPFCNPJProprietario");

document.addEventListener('DOMContentLoaded', function () {
	const maskCpfCnpj1 =IMask(proprietarioImovel, {
			        mask: [ 
						{mask: '000.000.000-00'},
						{mask: '00.000.000/0000-00'}
					]
	});
	const maskCpfCnpj2 =IMask(proprietarioCPFCPNJ, {
			        mask: [ 
						{mask: '000.000.000-00'},
						{mask: '00.000.000/0000-00'}
					]
	});
	
	selectProprietario.addEventListener('change', function() {
			maskCpfCnpj1.value = '';
			maskCpfCnpj2.value = '';
	});
	
	document.getElementById("tipoPessoaProprietarioSelect").addEventListener('change', function () {
			maskCpfCnpj1.value = '';
			maskCpfCnpj2.value = '';
	});
	
	//maskCpfCnpj1.on('complete', () => console.log(maskCpfCnpj1.unmaskedValue));
	//maskCpfCnpj2.on('complete', () => console.log(maskCpfCnpj2.unmaskedValue));
})

selectProprietario.addEventListener('change', function() {
	//console.log(selectProprietario.value);
	
    if(selectProprietario.value == 'criar') {
		proprietarioImovel.value = null;
		proprietarioCPFCPNJ.value= null;
		
    	divProprietario.style.gap = "20px";
		divProprietario.style.paddingTop = "20px";
		divProprietario.style.paddingBottom = "20px";
		    	
    	hidden.forEach(element => {
        	element.style.display = 'block';
    	});
	} else {
		proprietarioImovel.value = null;
		proprietarioCPFCPNJ.value= null;
		
		divProprietario.style.gap = "0px";
		divProprietario.style.paddingTop = "0px";
		divProprietario.style.paddingBottom = "00px";
		
		hidden.forEach(element => {
			element.style.display = "none";
		});
	}
}); 

proprietarioCPFCPNJ.addEventListener('change', function () {
	//console.log(proprietarioImovel.value);
	//console.log(proprietarioCPF.value);
	
	proprietarioImovel.value = proprietarioCPFCPNJ.value;
});

document.getElementById("tipoPessoaProprietarioSelect").addEventListener('change', function () {
	//console.log(document.getElementById("tipoPessoaProprietarioSelect").value);
	
	if (document.getElementById("tipoPessoaProprietarioSelect").value == 'F') {
		proprietarioCPFJNPJLabel.innerText = "CPF do Proprietário";
		//proprietarioCPFCPNJ.removeAttribute("maxlength");
		proprietarioCPFCPNJ.setAttribute("maxlength", 14);

	} else {
		proprietarioCPFJNPJLabel.innerText = "CNPJ do Proprietário";
		//proprietarioCPFCPNJ.removeAttribute("maxlength");
		proprietarioCPFCPNJ.setAttribute("maxlength", 18);
		
	};
});

document.getElementById('imagemInput').addEventListener('change', function () {
    let file = this.files[0];
    let reader = new FileReader();

    reader.onload = function (event) {
        let base64String = event.target.result;
        let PlaceHolder = document.querySelectorAll("#imgPlaceHolder");
        PlaceHolder[0].style.display = 'none';
        PlaceHolder[1].style.display = 'none';
        let preview = document.getElementById('preview');
        preview.src = base64String;
        preview.style.display = 'block';
        document.getElementById('imagemBase64').value = base64String;
    };

    reader.readAsDataURL(file);
});