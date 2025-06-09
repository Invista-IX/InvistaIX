const meses = ["Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"];
const receitaMensal = [1000,3000,3500,3700,3600,3600,3700,3800,3900,4000,4200,4100];
const despesaMensal = [0,2000,3000,3200,3100,3100,3200,3300,3400,3700,3600,3600];

window.addEventListener('DOMContentLoaded', () => {
    new Chart(document.getElementById("receita-despesa"), {
        type:'line',
        data:{
            labels:meses,
            datasets:[
                {label:"Receita", data:receitaMensal, borderColor:'green', tension:0.3},
                {label:"Despesa", data:despesaMensal, borderColor:'black', tension:0.3}
            ]
        },
        options:{ scales:{ y:{ beginAtZero:true }}}
    });
});