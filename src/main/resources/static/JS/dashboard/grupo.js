//  API  //

// cards imoveis
let idGrupo = document.getElementById('idGrupo').textContent;
console.log(idGrupo);
const URL_GRUPOS = "http://localhost:8080/imovel/findAllByGupo=" + idGrupo;

window.addEventListener("load", async () => {
    const wrapper = document.querySelector("#cards_container");

    try {
        // Buscar dados da API
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
                const div = criarElementoCardImovel(item);
                wrapper.appendChild(div);
            });
        } else {
            console.log("Nenhum dado encontrado na resposta da API.");
        }
    } catch (error) {
        console.error("Erro ao buscar dados da API:", error);
    }
});

function criarElementoCardImovel(item) {
	const base64String = "data:image/png;base64," + item.imagemBase64;
    console.log(base64String);

    const imagem = document.createElement("img");
    fetch(base64String)
        .then(res => res.blob())
        .then(blob => {
        const url = URL.createObjectURL(blob);
        imagem.classList.add("card-img");
        imagem.src = url;
        imagem.alt = "imagem_" + item.nome;
    })
    .catch(erro => {
        console.log(erro.message);
        
    });
    imagem.classList.add("card-img");
    imagem.alt = "imagem_" + item.nome;
    
    const nome = document.createElement("h3");    
    nome.classList.add("card-h3");
    nome.innerText = item.nome;

    const matricula = document.createElement("p");    
    matricula.classList.add("card-p");
    matricula.innerText = item.numero_matricula;

    const area = document.createElement("p");    
    area.classList.add("card-p");
    area.innerText = item.area;

    const preco = document.createElement("p");    
    preco.classList.add("card-p");
    preco.innerText = item.preco;

    const imovel_card = document.createElement("div");
    imovel_card.classList.add("card");
    imovel_card.appendChild(imagem);
    imovel_card.appendChild(nome);
    imovel_card.appendChild(matricula);
    imovel_card.appendChild(area);
    imovel_card.appendChild(preco);

    const imovel_link = document.createElement("a");
    imovel_link.href = "/imovel/grupo=" + idGrupo + "&imovel=" + item.id + "/gerenciar";
    imovel_link.append(imovel_card);

    return imovel_link;
};