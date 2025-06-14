window.addEventListener('DOMContentLoaded', async () => {
    let idImovel = document.getElementById('idImovel').textContent;
    console.log(idImovel);

    //chart valorização
    Highcharts.chart('container-valorizacao', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'Valorização do Imóvel'
        },
        xAxis: {
            categories: [
                'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set',
                'Out', 'Nov', 'Dec'
            ]
        },
        yAxis: {
            title: {
                text: 'Valor (R$)'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: 'Valor/Mês',
            color: '#00a650',
            data: [
                16.0, 18.2, 23.1, 27.9, 32.2, 36.4, 39.8, 38.4, 35.5, 29.2,
                22.0, 17.8
            ]
        }]
    });

    //chart performance
    buscarDados(idImovel);


    //chart lucro mensal
    Highcharts.chart('container-lucro', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Lucro Mensal'
        },
        xAxis: {
            type: 'category',
            labels: {
                autoRotation: [-45, -90],
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Valor (R$)'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: 'Lucro: <b>R${point.y:.2f}</b>'
        },
        series: [{
            name: 'Lucro',
            colors: [
                '#00a650',
            ],
            colorByPoint: true,
            groupPadding: 0,
            data: [
                ['Jan', 37.33],
                ['Fev', 31.18],
                ['Mar', 27.79],
                ['Abr', 22.23],
                ['Mai', 21.91],
                ['Jun', 21.74],
                ['Jul', 21.32],
                ['Ago', 20.89],
                ['Set', 20.67],
                ['Out', 19.11],
                ['Nov', 16.45],
                ['Dec', 16.38],
            ],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                inside: true,
                verticalAlign: 'top',
                format: '{point.y:.2f}', // one decimal
                y: 10, // 10 pixels down from the top
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        }]
    });

    //chart receitas e despesas mensais
    Highcharts.chart('container-receitasdespesas', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'Receitas/Despesas Mensais'
        },
        xAxis: {
            categories: [
                'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set',
                'Out', 'Nov', 'Dec'
            ]
        },
        yAxis: {
            title: {
                text: 'Valor (R$)'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: 'Receitas/Mês',
            color: '#00a650',
            data: [
                16.0, 18.2, 23.1, 27.9, 32.2, 36.4, 39.8, 38.4, 35.5, 29.2,
                22.0, 17.8
            ]
        }, {
            name: 'Despesas/Mês',
            color: '#FF0000',
            data: [
                -2.9, -3.6, -0.6, 4.8, 10.2, 14.5, 17.6, 16.5, 12.0, 6.5,
                2.0, -0.9
            ]
        }]
    });
});

async function buscarDados(imovelId) {
    const url = `http://localhost:8080/imovel/buscar/performance/${imovelId}`;

    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error(`Erro ${response.status}`);
        const data = await response.json();

        const meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];

        const ordenado = data
            .sort((a, b) => a.indice - b.indice)
            .map(item => ({
                valor: parseFloat(item.porcentagem),
                mes: meses[new Date(item.data).getMonth()]
            }));

        const valores = ordenado.map(item => item.valor);
        const categorias = ordenado.map(item => item.mes);

        gerarGrafico(valores, categorias);
    } catch (error) {
        console.error('Erro ao buscar performance:', error);
    }
}

function gerarGrafico(valores, categorias) {
    Highcharts.chart('container-performance', {
        chart: {
            type: 'line'
        },
        title: {
            text: 'Performance do Imóvel'
        },
        xAxis: {
            categories: categorias
        },
        yAxis: {
            title: {
                text: 'Performance (%)'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: 'Desempenho/Mês',
            color: '#00a650',
            data: valores
        }]
    });
}