document.addEventListener('DOMContentLoaded', () => {
    IMask(document.getElementById('cnpj'), {
        mask: '00.000.000/0000-00'
    });
    document.querySelector('form').addEventListener('submit', function () {
        const campos = document.querySelectorAll('.dinheiro');
        campos.forEach(input => {
            input.value = input.value
                .replace(/\./g, '')
                .replace(/,/g, '.');
        });
    });

    document.querySelectorAll('.dinheiro').forEach(input => {
        IMask(input, {
            mask: Number,
            scale: 2,
            signed: false,
            thousandsSeparator: '.',
            padFractionalZeros: true,
            normalizeZeros: true,
            radix: ',',
            mapToRadix: ['.']
        });
    });

    const tabs = document.querySelectorAll('.tab');
    const indicator = document.getElementById('indicator');
    const panes = document.querySelectorAll('.pane');

    function updateIndicator(tab) {
        const rect = tab.getBoundingClientRect();
        const parentRect = tab.parentElement.getBoundingClientRect();
        indicator.style.width = `${rect.width}px`;
        indicator.style.transform = `translateX(${rect.left - parentRect.left}px)`;
    }

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {

            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            updateIndicator(tab);

            panes.forEach(p => p.classList.remove('active'));
            document.getElementById(tab.dataset.target).classList.add('active');
        });
    });


    const init = () => updateIndicator(document.querySelector('.tab.active'));
    window.addEventListener('load', init);
    window.addEventListener('resize', init);
});

function enviarDespesa(event) {
    try {
        event.preventDefault();

        const form = event.target;
        const idImovel = document.getElementById('idImovel').value;

        if (!idImovel) {
            exibirModalErro('ID do imóvel não foi definido.');
            return;
        }

        const despesaData = {};
        form.querySelectorAll('.dinheiro').forEach(input => {
            let valorTratado = input.value.replace(/\./g, '').replace(',', '.');
            despesaData[input.name] = valorTratado;
        });

        despesaData['idImovel'] = idImovel;

        console.log(despesaData);

        const body = new URLSearchParams();
        for (const campo in despesaData) {
            body.append(campo, despesaData[campo]);
        }

        console.log(body);

        fetch(`/despesa/criar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: body.toString(),
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
                mostrarToastSucesso('Despesa salva com sucesso!');
                form.reset();
            })
            .catch(error => {

                exibirModalErro(error.message);
            });

    } catch (err) {
        exibirModalErro('Erro ao processar a despesa: ' + err.message());
    }
}


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
