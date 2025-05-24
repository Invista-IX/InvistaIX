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

     const formImposto = document.getElementById('formImposto');
        if (formImposto) {
            formImposto.addEventListener('submit', enviarImposto);
        }


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

////////INPUT DE DATA/////////////////
const hoje = new Date();
const elems = document.querySelectorAll('.inputData');
Datepicker.locales['pt-BR'] = {
    days: ['Domingo', 'Segunda-feira', 'Terça-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'Sábado'],
    daysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
    daysMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
    months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
        'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
        'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    today: 'Hoje',
    clear: 'Limpar',
    titleFormat: 'MM yyyy',
    format: 'dd/mm/yyyy',
    weekStart: 0
};

elems.forEach(elem => {
    new Datepicker(elem, {
        format: 'dd/mm/yyyy',
        language: 'pt-BR',
        autohide: true,
        clearBtn: true,
        maxDate: hoje
    });
});

elems.addEventListener('changeDate', function (e) {
    const date = e.detail.date;
    if (date) {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        elems.value = `${day}/${month}/${year}`;
    }
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
        form.querySelectorAll('.dinheiroDespesa').forEach(input => {
            let valorTratado = input.value.replace(/\./g, '').replace(',', '.');
            despesaData[input.name] = valorTratado;
        });

        despesaData['idImovel'] = idImovel;
        const dataInput = document.getElementById('dataDespesa');
        if (dataInput && dataInput.value) {
            despesaData['data'] = dataInput.value;
        }

        const body = new URLSearchParams();
        for (const campo in despesaData) {
            body.append(campo, despesaData[campo]);
        }

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


function enviarReceita(event) {
    try {
        event.preventDefault();

        const form = event.target;
        const idImovel = document.getElementById('idImovel').value;

        if (!idImovel) {
            exibirModalErro('ID do imóvel não foi definido.');
            return;
        }

        const receitaData = {};
        form.querySelectorAll('.dinheiroReceita').forEach(input => {
            let valorTratado = input.value.replace(/\./g, '').replace(',', '.');
            receitaData[input.name] = valorTratado;
        });
        receitaData['idImovel'] = idImovel;
        const dataInput = document.getElementById('dataReceita');
        if (dataInput && dataInput.value) {
            receitaData['data'] = dataInput.value;
        }

        const body = new URLSearchParams();
        for (const campo in receitaData) {
            body.append(campo, receitaData[campo]);
        }

        fetch(`/receita/criar`, {
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
                mostrarToastSucesso('Receita salva com sucesso!');
                form.reset();
            })
            .catch(error => {

                exibirModalErro(error.message);
            });

    } catch (err) {
        exibirModalErro('Erro ao processar a Receita: ' + err.message());
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

function enviarImposto(event) {
    try {
        event.preventDefault();

        const form = event.target;
        const idimovel = document.getElementById('idimovel').value;

        if (!idimovel) {
            exibirModalErro('ID do imóvel não foi definido.');
            return;
        }

        const impostoData = {};
        form.querySelectorAll('.dinheiro').forEach(input => {
            let valorTratado = input.value.replace(/\./g, '').replace(',', '.');
            impostoData[input.name] = valorTratado;
        });

        const data = document.getElementById('data').value;
        if (!data) {
            exibirModalErro('A data do IPTU deve ser informada.');
            return;
        }
        impostoData['data'] = data;

        impostoData['idimovel'] = idimovel;

        const body = new URLSearchParams();
        for (const campo in impostoData) {
            body.append(campo, impostoData[campo]);
        }

        fetch(`/iptu/criar`, {
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
                mostrarToastSucesso('Imposto salvo com sucesso!');
                form.reset();
            })
            .catch(error => {
                exibirModalErro(error.message);
            });

    } catch (err) {
        exibirModalErro('Erro ao processar o imposto: ' + err.message);
    }
}
