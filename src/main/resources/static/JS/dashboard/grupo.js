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
	
	const matricula_value = document.createElement("p");
	matricula_value.classList.add("card-p");
	matricula_value.innerText = item.numeroMatricula;
	
    const matricula = document.createElement("p");    
    matricula.classList.add("card-p");
	matricula.classList.add("card-matricula");
    matricula.innerText = "Numero da Matricula";
	matricula.appendChild(document.createElement("br"));
	matricula.appendChild(matricula_value);
	
	const preco_value = document.createElement("p");
	preco_value.classList.add("card-p");
	preco_value.innerText = item.preco;
	
	const preco = document.createElement("p");    
	preco.classList.add("card-p");
	preco.classList.add("card-preco");
	preco.innerText = "Valor";
	preco.appendChild(document.createElement("br"));
	preco.appendChild(preco_value);
	
	const area_value = document.createElement("p");
	area_value.classList.add("card-p");
	area_value.innerText = item.area;

    const area = document.createElement("p");    
    area.classList.add("card-p");
	area.classList.add("card-area");
    area.innerText = "Área";
	area.appendChild(document.createElement("br"));
	area.appendChild(area_value);
	
	const botao_gerenciar = document.createElement("button");
	botao_gerenciar.classList.add("gerenciar_imovel");
	botao_gerenciar.innerText = "Gerenciar Imóvel"
	
	const gerenciar_link = document.createElement("a");
	gerenciar_link.classList.add("gerenciar_link");
	gerenciar_link.href = "/imovel/grupo=" + idGrupo + "&imovel=" + item.id + "/gerenciar";
	gerenciar_link.append(botao_gerenciar);
	
	const botao_analisar = document.createElement("button");
	botao_analisar.classList.add("analisar_imovel");
	botao_analisar.innerText = "Analisar Imóvel"

	const analisar_link = document.createElement("a");
	analisar_link.classList.add("analisar_link");
	analisar_link.href = "/imovel/grupo=" + idGrupo + "&imovel=" + item.id + "/graficos";
	analisar_link.append(botao_analisar);
	
    const imovel_card = document.createElement("div");
    imovel_card.classList.add("card");
    imovel_card.appendChild(imagem);
    imovel_card.appendChild(nome);
    imovel_card.appendChild(matricula);
    imovel_card.appendChild(area);
    imovel_card.appendChild(preco);
	imovel_card.appendChild(gerenciar_link);
	imovel_card.appendChild(analisar_link);

    return imovel_card;
};