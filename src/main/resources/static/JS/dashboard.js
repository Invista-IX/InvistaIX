//  API  //

// cards produtos 
const usuarioId = document.getElementById('usuarioId').textContent;
const URL_GRUPOS = "http://localhost:8080/grupos/encontrarGrupos=" + usuarioId;

window.addEventListener("load", async () => {
    const wrapper = document.querySelector("#cards_container");

    try {
        // Buscar dados do Sanity
        const result = await fetch(URL_GRUPOS);
        console.log(result);
        const data = await result.json();
        console.log(data);
		const dados = data;
		console.log(dados);
        console.log(dados.length);

        // Verificar se os dados existem
        if (data && data.length) {
            dados.forEach(item => {
                const div = criarElementoCardGrupo(item);
                wrapper.appendChild(div);
            });
        } else {
            console.log("Nenhum dado encontrado na resposta da API.");
        }
    } catch (error) {
        console.error("Erro ao buscar dados da API:", error);
    }
});

function criarElementoCardGrupo(item) {
    console.log(item.imagem_base64);
    base64String = item.imagem_base64;
    const imagem = document.createElement("img");
    fetch(base64String)
        .then(res => res.blob())
        .then(blob => {
        const url = URL.createObjectURL(blob);
        imagem.classList.add("card-img");
        imagem.src = url;
        imagem.alt = "imagem_" + item.nome;
    });
    
    const nome = document.createElement("h3");    
    nome.classList.add("card-h3");
    nome.innerText = item.nome;

    const codigo_value = document.createElement("p");
    codigo_value.classList.add("card-p");
    codigo_value.innerText = item.codigo;

    const codigo = document.createElement("p");    
    codigo.classList.add("card-p");
    codigo.classList.add("card-p-t");
    codigo.innerText = "Códgio";
    codigo.appendChild(document.createElement("br"));
    codigo.appendChild(codigo_value);

    const gestores_value = document.createElement("p");
    gestores_value.classList.add("card-p");
    const url = "http://localhost:8080/grupos/" + item.id + "/totalGestores"
    fetch(url, {
        method: "GET"
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
        gestores_value.innerText = data;
    })
    .catch(erro=> {
        console.log(erro);
    })

    const gestores = document.createElement("p");
    gestores.classList.add("card-p");
    gestores.classList.add("card-p-t");
    gestores.innerText = "Total Gestores";
    gestores.appendChild(gestores_value);

    const grupo_card = document.createElement("div");
    grupo_card.classList.add("card");
    grupo_card.appendChild(imagem);
    grupo_card.appendChild(nome);
    grupo_card.appendChild(codigo);
    grupo_card.appendChild(gestores);

    const grupo_link = document.createElement("a");
    grupo_link.href = "/dashboard/grupo=" + item.id;
    grupo_link.append(grupo_card);

    return grupo_link;
};

function exibirModalErro(mensagem) {
    const modal = document.getElementById("modalErro");
    const texto = document.getElementById("mensagemErroTexto");

    texto.textContent = mensagem;
    modal.style.display = "flex";
}

function fecharModalErro() {
    const modal = document.getElementById("modalErro");
    modal.style.display = "none";
}

function exibirModalIngressar() {
    const modal = document.getElementById("modalIngressar");
    const texto = document.getElementById("mensagemIngressarTexto");

    texto.textContent = "Insira a senha e o código do grupo que deseja ingressar";
    modal.style.display = "grid";
}

function fecharModalIngressar() {
    const modal = document.getElementById("modalIngressar");
    modal.style.display = "none";
}

document.addEventListener('click', function (event) {
    const modal = document.getElementById('modalErro');
    const conteudo = document.querySelector('#modalErro .modal-content');
    if (modal.style.display === 'flex' && !conteudo.contains(event.target)) {
        fecharModalErro();
    }
});
document.getElementById("modalErro").addEventListener("click", function (e) {
    if (e.target === this) {
        fecharModalErro();
    }
});

function mostrarToastSucesso(mensagem) {
    const toast = document.getElementById("toastSucesso");
    toast.textContent = mensagem;
    toast.classList.add("mostrar");

    setTimeout(() => {
        toast.classList.remove("mostrar");
    }, 3000);
}

function ingressarGrupo() {
    try {
        const codigo = document.getElementById("codigogrupoInput").value;
        const senha = document.getElementById("senhaGrupoInput").value;
        const urlGrupo = "http://localhost:8080/grupos/encontrar/codigo=" + codigo + "&senha=" + senha;
        console.log(urlGrupo);
        fetch(urlGrupo, {
            method: 'GET'
        })
        .then(response => {
            if(!response.ok) {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }
            return response.json();
        })
        .then(grupo => {
            const urlAtribuir = "http://localhost:8080/grupos/" + grupo.id + "/adicionarGestor=" + usuarioId;
            
            console.log(grupo);
            console.log(urlAtribuir);
            
            fetch(urlAtribuir, {
                method: 'PUT'
            })
            .then(response => {
                if(!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    })
                }
                return response.text();
            })
            .then(data => {
                mostrarToastSucesso(data);
                window.location.reload();
            });
        })
        .catch(erro => {
            fecharModalIngressar();
            exibirModalErro(erro.message);
        })
    } catch (err) {
        fecharModalIngressar();
        exibirModalErro('Erro ao excluir imóvel: ' + err.message);
    }
}