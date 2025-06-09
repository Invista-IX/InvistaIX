//  API  //

// cards produtos 
const URL_GRUPOS = "http://localhost:8080/api/grupos";

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
	const base64String = "data:image/png;base64," + item.imagem_base64;

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

    const codigo = document.createElement("p");    
    codigo.classList.add("card-p");
    codigo.innerText = item.codigo;

    const grupo_card = document.createElement("div");
    grupo_card.classList.add("card");
    grupo_card.appendChild(imagem);
    grupo_card.appendChild(nome);
    grupo_card.appendChild(codigo);

    const grupo_link = document.createElement("a");
    grupo_link.href = "/dashboard/grupo=" + item.id;
    grupo_link.append(grupo_card);

    return grupo_link;
};