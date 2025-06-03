function validarFormulario() {
        const email = document.getElementById("email").value.trim();
        const senha = document.getElementById("senha").value.trim();

        if (!email || !senha) {
            alert("Preencha todos os campos!");
            return false;
        }

        const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regexEmail.test(email)) {a
            alert("Digite um email válido!");
            return false;
        }

        return true;
    }