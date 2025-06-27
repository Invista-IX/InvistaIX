const popup = document.getElementById('popup-overlay');
const btnExcluir = document.querySelector('.btn-lixeira');

btnExcluir.addEventListener('click', function (e) {
  e.preventDefault();
  popup.style.display = 'flex';
});

function fecharPopup() {
  popup.style.display = 'none';
}

function confirmarExclusao() {
  fetch('/perfil/excluir', {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'

    }
  })
  .then(response => {
    if (response.ok) {
      alert('Conta excluída com sucesso');
      window.location.href = '/login';
    } else {
      alert('Erro ao excluir a conta.');
    }
  });
}
