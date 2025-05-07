document.getElementById('formCadastro').addEventListener('submit', function (e) {
    const senha = document.getElementById('senha').value;
    const confSenha = document.getElementById('confSenha').value;
    const erroSpan1 = document.getElementById('erroSenha');
    const erroSpan2 = document.getElementById('erroConfSenha');

    if (senha !== confSenha) {
        e.preventDefault(); 
        erroSpan1.textContent = 'As senhas não coincidem.';
        erroSpan1.style.display = 'block';
        erroSpan2.textContent = 'As senhas não coincidem';
        erroSpan2.style.display = 'block';
    } else {
        erroSpan1.style.display = 'none'; 
        erroSpan2.style.display = 'none';
    }
});