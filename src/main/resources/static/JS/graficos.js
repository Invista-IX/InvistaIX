window.addEventListener('DOMContentLoaded', async () => {
    let idImovel = document.getElementById('idImovel').textContent;
    console.log(idImovel);

    //chart performance
    buscarDados(idImovel);

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
	

	//chart lucro mensal
    fetch(`/api/graficos/ReceitaDespesa/${idImovel}`)
      .then(response => response.json())
      .then(data => {
        const lucroMensal = data.meses.map((mes, index) => {
          const valor = data.lucro[index];
          return {
            name: mes,
            y: valor,
            color: valor >= 0 ? '#00a650' : '#d9534f'
          };
        });

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
            data: lucroMensal,
            dataLabels: {
              enabled: false,
              rotation: -90,
              color: '#FFFFFF',
              inside: true,
              verticalAlign: 'top',
              format: '{point.y:.2f}',
              y: 10,
              style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
              }
            }
          }]
        });
      });

	//chart receitas e despesas mensais
	try {
		const response = await fetch(`/api/graficos/ReceitaDespesa/${idImovel}`);
		if (!response.ok) throw new Error('Erro ao buscar dados de receitas/despesas');
		const data = await response.json();

		Highcharts.chart('container-receitasdespesas', {
			chart: {
				type: 'line'
			},
			title: {
				text: 'Receitas/Despesas Mensais'
			},
			xAxis: {
				categories: data.meses
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
			series: [
				{
					name: 'Receitas/Mês',
					color: '#00a650',
					data: data.receita
				},
				{
					name: 'Despesas/Mês',
					color: '#FF0000',
					data: data.despesa
				}
			]
		});
	} catch (error) {
		console.error('Erro ao carregar gráfico de receitas/despesas:', error);
	}
});

