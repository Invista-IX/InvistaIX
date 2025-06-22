const idUsuario = document.getElementById("idUsuario").textContent;

//mudar o preview
document.getElementById('input_imagem').addEventListener('change', function () {
    let file = this.files[0];
    let reader = new FileReader();

    reader.onload = function (event) {
        let base64String = event.target.result;
        console.log(base64String);
        document.getElementById('string_imagem').value = base64String;
        document.getElementById('imagem').src = base64String;
    };


    reader.readAsDataURL(file);
});

document.getElementById('')

async function encontrarGrupo(codigo, senha) {
    try{
        let idGrupo;
        if(!codigo) {
            throw new Error("Código inválido");
        }
        if(!senha){
            throw new Error("Senha inválida");
        }

        const url = "http://localhost:8080/grupos/encontrar/codigo=" + codigo + "&senha=" + senha;
        console.log(url);

        await fetch(url, {
            method: "GET",    
        })
        .then(response => {
            if(!response.ok) {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }
            
            return response.json();
        })
        .then(data => {
            console.log(data);
            console.log(data.id);
            idGrupo = data.id;
        })
        .catch(erro => {
            console.log(erro.message);
        })
        return idGrupo; 
    } catch(erro) {
        console.log(erro);
    }
}

async function enviarImagem(idGrupo) {
    try {
        if(!idGrupo){
            throw new Error("ID do grupo inválido");
        }

        const imagem = document.getElementById("string_imagem");

        const url = "http://localhost:8080/grupos/" + idGrupo + "/atualizarImagem";
        console.log(url);

        const fd = new FormData();
        fd.append('base64', imagem.value === '' ? null : imagem.value);

        console.log(Array.from(fd));
		let body = new URLSearchParams(fd);
        console.log(body);
        
        await fetch(url, {
            method: "PUT",
			body: body,
        })
        .then(response => {
            if(!response.ok) {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }

            return response.text(); 
        })
        .then(text => {
            console.log(text);
        })
        .catch(erro => {
            console.log(erro.message);
        })
    } catch(erro) {
        console.log(erro.message);
    }
}

async function enviarGrupo(event) {
    event.preventDefault();
    try {
        const url = "http://localhost:8080/grupos/cadastrar"
        console.log(url);

        const inputs = document.querySelectorAll('.grupoInput');
        console.log(inputs);

        const fd = new FormData();
        inputs.forEach(input => {
            fd.append(input.name, input.value);
        })
        console.log(Array.from(fd));
        const body = new URLSearchParams(fd);

        await fetch(url, {
            method: "POST",
            headers: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            body: body,
        })
        .then(response => {
            if(!response.ok) {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }

            return response.text();
        })
        .then(async text => {
            console.log(text);
            
            let idGrupo = await encontrarGrupo(document.getElementById("codigo_input").value, document.getElementById("senha_input").value);
            console.log(idGrupo);

            await enviarImagem(idGrupo);

            let url = "http://localhost:8080/grupos/" + idGrupo + "/adicionarGestor=" + idUsuario;

            fetch(url, {
                method: "PUT"
            })
            .then(response => {
                if(!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }

                return response.text();
            })
            .then(text =>{
                console.log(text);
                console.log("e não é que funcionou msm?");
                location.replace("http://localhost:8080/dashboard");
            })
            .catch(erro => {
                console.log(erro);
            })
        })
    } catch(erro) {
        console.log(erro);
    }
};