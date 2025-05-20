document.getElementById('imagemInput').addEventListener('change', function () {
    let file = this.files[0];
    let reader = new FileReader();

    reader.onload = function (event) {
        let base64String = event.target.result;
        document.getElementById('imagem').src = base64String;
        document.getElementById('imagemBase64').value = base64String;
    };

    reader.readAsDataURL(file);
});