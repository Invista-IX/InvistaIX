const valorMatricula = document.getElementById('valorMatriculaImovelInput');

const selectProprietario = document.getElementById("proprietarioImovelSelectInput");
const divProprietario = document.getElementById("cadastroProprietario");
const hidden = document.querySelectorAll(".toggleProprietario");
const inputsProprietario = document.querySelectorAll('.proprietarioInput');

const proprietarioImovel = document.getElementById("propritarioImovelInput");
const proprietarioCPFCPNJ = document.getElementById("cpfCpnjProprietarioInput");
const proprietaioTelefone = document.getElementById("telefoneProprietarioInput")

const imovelValorMatricula = document.getElementById('valorMatriculaImovelInput');
const imovelPreco = document.getElementById('precoImovelInput');

const proprietarioCPFJNPJLabel = document.getElementById("labelCPFCNPJProprietario");

const fakeEstado = document.getElementById("fakeEstadoEnderecoInput");
const cepEndereco = document.getElementById("cepEnderecoInput");

const idGrupo = document.getElementById("idGrupo");
const idImovel = document.getElementById("idImovel");

document.addEventListener('DOMContentLoaded', function () {
	const maskCpfCnpj1 = IMask(proprietarioImovel, {
			        mask: [ 
						{mask: '000.000.000-00'},
						{mask: '00.000.000/0000-00'}
					]
	});
	
	const maskCpfCnpj2 = IMask(proprietarioCPFCPNJ, {
			        mask: [ 
						{mask: '000.000.000-00'},
						{mask: '00.000.000/0000-00'}
					]
	});

	const maskCep = IMask(cepEndereco, {
					mask: [
						{mask:'00000-000'}
					]
	});
	
	const maskValorMatricula = IMask(valorMatricula, {
		mask: Number,
		scale: 2,
		signed: false,
		thousandsSeparator: '.',
		padFractionalZeros: true,
		normalizeZeros: true,
		radix: ',',
		mapToRadix: ['.']
	});
	
	const maskPreco = IMask(imovelPreco, {
		mask: Number,
		scale: 2,
		signed: false,
		thousandsSeparator: '.',
		padFractionalZeros: true,
		normalizeZeros: true,
		radix: ',',
		mapToRadix: ['.']
	});
	
	let valorMatriculaDinheiro = Number(document.getElementById("fakeValorMatriculaImovelInput").value);
	maskValorMatricula.value = String(valorMatriculaDinheiro);
	
	let precoDinheiro = Number(document.getElementById("fakePrecoImovelInput").value);
	console.log(precoDinheiro);
	maskPreco.value = String(precoDinheiro);
	
	selectProprietario.addEventListener('change', function() {
			maskCpfCnpj1.value = '';
			maskCpfCnpj2.value = '';
	});
	
	document.getElementById("tipoPessoaProprietarioSelect").addEventListener('change', function () {
			maskCpfCnpj1.value = '';
			maskCpfCnpj2.value = '';
	});

	divProprietario.style.gap = "20px";
	divProprietario.style.paddingTop = "20px";
	divProprietario.style.paddingBottom = "20px";
	    	
	hidden.forEach(element => {
		element.style.display = 'block';
	});

	inputsProprietario.forEach(input => {
		if (input.name != 'telefone') {
			input.setAttribute('required', true);
		}
	});
	
	let PlaceHolder = document.querySelectorAll("#imgPlaceHolder");
	PlaceHolder[0].style.display = 'none';
	PlaceHolder[1].style.display = 'none';
	let preview = document.getElementById('preview');
	preview.src = document.getElementById('fakeImagemBase64').value;
	preview.style.display = 'block';
	
	let estado = fakeEstado.value;
	console.log(estado);
	for(var i = 0, elementos = document.getElementById('estadoEnderecoInput'), l = elementos.length; i < l; i++) {
		if (elementos[i].value == fakeEstado.value) {
			elementos[i].selected = fakeEstado.value;
		}
	}
})

proprietarioCPFCPNJ.addEventListener('change', function () {
	proprietarioImovel.value = proprietarioCPFCPNJ.value;
});


document.getElementById("tipoPessoaProprietarioSelect").addEventListener('change', function () {

	if (document.getElementById("tipoPessoaProprietarioSelect").value == 'F') {
		proprietarioCPFJNPJLabel.innerText = "CPF do Proprietário";
		proprietarioCPFCPNJ.setAttribute("maxlength", 14);

	} else {
		proprietarioCPFJNPJLabel.innerText = "CNPJ do Proprietário";
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

async function enviarEndereco() {
	try {
		
		const form = document.getElementById('cad_imovel');

		let fd = new FormData();
		form.querySelectorAll('.enderecoInput').forEach(input => {
			if(input.name == "CEP") {
				const maskCep = IMask(cepEndereco, {
									mask: [
										{mask:'00000-000'}
									]
				});
				fd.append(input.name, maskCep.unmaskedValue);
			} else {
				fd.append(input.name, input.value);
			}
		});

		console.log(Array.from(fd));

		const body = new URLSearchParams(fd);

		
		fetch(`/endereco/salvarEndereco`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: body,
		})
			.then(response => {
				if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
                return response.text();
			})
			.then(data => {
				console.log('endereco sucesso');	
			})
			.catch(error => {
                console.log(error.message);
            });
		
	} catch (erro) {
		console.log("erro :P - "  + erro.message);
	}
}

async function enviarProprietario() {
	try {

		const form = document.getElementById('cad_imovel');

		let fd = new FormData();
		form.querySelectorAll('.proprietarioInput').forEach(input => {
			if(input.name == "cnpjCpf") {
				const maskCpfCnpj2 = IMask(proprietarioCPFCPNJ, {
							        		mask: [ 
												{mask: '000.000.000-00'},
												{mask: '00.000.000/0000-00'}
											]
				});
				fd.append(input.name, maskCpfCnpj2.unmaskedValue);
			} else {
				fd.append(input.name, input.value);
			}
		});

		console.log(Array.from(fd));

		const body = new URLSearchParams(fd);

		
		await fetch(`/proprietario/salvarProprietario`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: body,
		})
			.then(response => {
				if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
                return response.text();
			})
			.then(data => {
				console.log('proprietario sucesso');	
			})
			.catch(error => {
                console.log(error.message);
            });
		
	} catch (erro) {
		console.log("erro :P - " + erro.message);
	}
}

let idEndereco;

async function enviarImovel(idEndereco, idProprietario) {
	try {

		let form = document.getElementById('cad_imovel')
		let fd = new FormData();

		form.querySelectorAll('.imovelInput').forEach(input => {
			if(input.name == "cnpjCpf") {
				const maskCpfCnpj1 = IMask(proprietarioImovel, {
			        mask: [ 
						{mask: '000.000.000-00'},
						{mask: '00.000.000/0000-00'}
					]
				});
				fd.append(input.name, maskCpfCnpj1.unmaskedValue);
			} else if(input.name === "valorMatricula" || input.name === "preco"){
				fd.append(input.name, input.value.replace(/\./g, '').replace(/,/g, '.'));
			} else {
				fd.append(input.name, input.value);
			}
		});
		fd.append('imagemBase64', document.getElementById('preview').src);

		fd.append('idProprietario', idProprietario);
		
		fd.append('endereco', idEndereco);

		console.log(Array.from(fd));
		let body = new URLSearchParams(fd);

		await fetch(`/imovel/salvarImovel`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: body,
		})
			.then(response => {
				if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
                return response.text();
			})
			.then(data => {
				console.log('imovel sucesso');
				const url_redirect = "http://localhost:8080/imovel/grupo=" + idGrupo.textContent + "&imovel=" + idImovel.textContent + "/gerenciar"	
				location.replace(url_redirect);
			})
			.catch(error => {
                console.log(error.message);
            });
		
	} catch (erro) {
		console.log("erro :P - " + erro.message);
	}
}

 async function  mandarImovel(event) {
	event.preventDefault();
	
	const maskCpfCnpj1 = IMask(proprietarioImovel, {
		mask: [ 
			{mask: '000.000.000-00'},
			{mask: '00.000.000/0000-00'}
		]
	});
	
	const maskCep = IMask(cepEndereco, {
										mask: [
											{mask:'00000-000'}
										]
	});

	let addressProprietario = "http://localhost:8080/proprietario/findByCnpjCpf=" + maskCpfCnpj1.unmaskedValue;
	console.log(addressProprietario);
	
	await enviarProprietario();

	await enviarEndereco();

	let rua = document.getElementById('ruaEnderecoInput').value;
	let numero = document.getElementById('numeroEnderecoInput').value;
	let loteamento = document.getElementById('loteamentoEnderecoInput').value;
	let cidade = document.getElementById('cidadeEnderecoInput').value;
	let estado = document.getElementById('estadoEnderecoInput').value;
	let cep = document.getElementById('cepEnderecoInput').value;
	
	let addressEndereco = "http://localhost:8080/endereco/findByEndereco=" + rua + "&" + numero + "&" + loteamento + "&" + cidade + "&" + estado + "&" + maskCep.unmaskedValue; 
	console.log(addressEndereco);

	enviarImovel(1, 1);
	
	await fetch(addressProprietario)
		.then(response => {
			if (!response.ok) {
				return response.text().then(text => {
					throw new Error(text);
				});
			}
			return response.json();
		})
		.then(async proprietario => {
			await fetch(addressEndereco)
				.then(response => {
					if (!response.ok) {
						return response.text().then(text => {
							throw new Error(text);
						});
					}
					return response.json();
				})
				.then(endereco => {
					console.log(endereco);
					console.log(proprietario);

					enviarImovel(endereco.id, proprietario.id);
				})
				.catch(erro => {
					console.warn(erro.message);
				});
		})
		.catch(erro => {
			console.log(erro.message);
		}) ;
		
}