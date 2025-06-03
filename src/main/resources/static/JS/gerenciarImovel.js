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

<<<<<<< HEAD
=======
     const formImposto = document.getElementById('formImposto');
        if (formImposto) {
            formImposto.addEventListener('submit', enviarImposto);
        }

        const inputArquivo = document.getElementById('docAvaliacao');
        const nomeArquivo = document.getElementById('nomeArquivo');

        inputArquivo.addEventListener('change', () => {
          if (inputArquivo.files.length > 0) {
            nomeArquivo.textContent = inputArquivo.files[0].name;
          } else {
            nomeArquivo.textContent = '';
          }
        });


>>>>>>> dev
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

<<<<<<< HEAD
=======
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

>>>>>>> dev
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
<<<<<<< HEAD
        form.querySelectorAll('.dinheiro').forEach(input => {
=======
        form.querySelectorAll('.dinheiroDespesa').forEach(input => {
>>>>>>> dev
            let valorTratado = input.value.replace(/\./g, '').replace(',', '.');
            despesaData[input.name] = valorTratado;
        });

        despesaData['idImovel'] = idImovel;
<<<<<<< HEAD

        console.log(despesaData);
=======
        const dataInput = document.getElementById('dataDespesa');
        if (dataInput && dataInput.value) {
            despesaData['data'] = dataInput.value;
        }
>>>>>>> dev

        const body = new URLSearchParams();
        for (const campo in despesaData) {
            body.append(campo, despesaData[campo]);
        }

<<<<<<< HEAD
        console.log(body);

=======
>>>>>>> dev
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


<<<<<<< HEAD
=======
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


>>>>>>> dev
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
<<<<<<< HEAD
=======

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

function enviarAvaliacao(event) {
    try {
        event.preventDefault();

        const form = event.target;
        const idimovel = document.getElementById('idimovel').value;

        if (!idimovel) {
            exibirModalErro('ID do imóvel não foi definido.');
            return;
        }

        const dataAvaliacao = {};
        form.querySelectorAll('.dinheiro').forEach(input => {
            let valorTratado = input.value.replace(/\./g, '').replace(',', '.');
            dataAvaliacao[input.name] = valorTratado;
        });

        const cnpj = document.getElementById('cnpj').value.trim();
        if (!cnpj) {
            exibirModalErro('O CNPJ deve ser informado.');
            return;
        }

        const razaoSocial = document.getElementById('razaoSocial').value.trim();
        if (!razaoSocial) {
            exibirModalErro('A razão social deve ser informada.');
            return;
        }

        const valorInput = document.getElementById('valorAvaliacao');
        let valor = valorInput.value.trim();
        if (!valor) {
            exibirModalErro('O valor da avaliação deve ser informado.');
            return;
        }
        let valorTratado = valor.replace(/\./g, '').replace(',', '.');

        const data = document.getElementById('dataAvaliacao').value;
        if (!data) {
            exibirModalErro('A data da avaliação deve ser informada.');
            return;
        }

        const fileInput = document.getElementById('docAvaliacao');
        if (!fileInput || fileInput.files.length === 0) {
            exibirModalErro('O documento da avaliação (PDF) deve ser enviado.');
            return;
        }

        const file = fileInput.files[0];
        if (file.type !== "application/pdf") {
            exibirModalErro('Apenas arquivos PDF são permitidos.');
            return;
        }

        const formData = new FormData();
        formData.append('cnpj', cnpj);
        formData.append('razaoSocial', razaoSocial);
        formData.append('valorAvaliacao', valorTratado);
        formData.append('dataAvaliacao', data);
        formData.append('idimovel', idimovel);
        formData.append('docAvaliacaoFile', file);

        fetch('/avaliacao/criar', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(errorMessage => {
                    throw new Error(errorMessage);
                });
            }
            return response.text();
        })
        .then(idAvaliacao => {
            document.getElementById('idAvaliacaoInput').value = idAvaliacao;
            mostrarToastSucesso('Avaliação salva com sucesso!');

            form.querySelectorAll('input:not(#idAvaliacaoInput)').forEach(input => input.value = '');

            return fetch(`/avaliacao/base64?idAvaliacao=${idAvaliacao}`)
                    .then(response => {
                        if (!response.ok) throw new Error("Erro ao buscar PDF.");
                        return response.text();
                    })
                    .then(base64String => {
                        const byteCharacters = atob(base64String);
                        const byteNumbers = Array.from(byteCharacters).map(ch => ch.charCodeAt(0));
                        const byteArray = new Uint8Array(byteNumbers);
                        const blob = new Blob([byteArray], { type: 'application/pdf' });

                        /*const link = document.createElement('a');
                        link.href = URL.createObjectURL(blob);
                        link.download = 'avaliacao.pdf';
                        document.body.appendChild(link);
                        link.click();
                        document.body.removeChild(link);*/
                    });
            })
            .catch(error => {
                console.error("Erro:", error);
                exibirModalErro(error.message || "Erro ao salvar ou baixar o PDF.");
            });

    } catch (err) {
        exibirModalErro('Erro ao processar a avaliação: ' + err.message);
    }
}


>>>>>>> dev
