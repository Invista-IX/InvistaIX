document.getElementById('formCadastro').addEventListener('submit', function (e) {
    const senha = document.getElementById('senha').value.trim();
    const confSenha = document.getElementById('confSenha').value.trim();
    const erroSpan1 = document.getElementById('erroSenha');
    const erroSpan2 = document.getElementById('erroConfSenha');

    if (senha !== confSenha) {
        e.preventDefault(); 
        erroSpan1.textContent = 'As senhas não coincidem.';
        erroSpan1.style.display = 'block';
        erroSpan2.textContent = 'As senhas não coincidem';
        erroSpan2.style.display = 'block';
        document.getElementById('senha').focus();
    } else {
        erroSpan1.style.display = 'none'; 
        erroSpan2.style.display = 'none';
    }

    if (senha !== confSenha) {
            e.preventDefault();
            erroSpan1.textContent = 'As senhas não coincidem.';
            erroSpan1.style.display = 'block';
            erroSpan2.textContent = 'As senhas não coincidem.';
            erroSpan2.style.display = 'block';
            document.getElementById('senha').focus();
            return;
    }
});