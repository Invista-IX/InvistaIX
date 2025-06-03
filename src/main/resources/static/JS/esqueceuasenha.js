 document.addEventListener('DOMContentLoaded', function () {
      const form = document.getElementById('form-esqueci-senha');
      const mensagemDiv = document.getElementById('mensagem');

      form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const email = document.getElementById('email').value;

        try {
          const response = await fetch('/esqueceuasenha', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `email=${encodeURIComponent(email)}`,
          });

          const data = await response.json();

          mensagemDiv.textContent = data.mensagem;
          mensagemDiv.style.color = data.status === 'ok' ? 'green' : 'red';

        } catch (error) {
          mensagemDiv.textContent = 'Erro ao enviar solicitação.';
          mensagemDiv.style.color = 'red';
        }
      });
    });