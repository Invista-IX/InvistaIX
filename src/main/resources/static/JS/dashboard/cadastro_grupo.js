//mudar o preview
document.getElementById('input_imagem').addEventListener('change', function () {
    let file = this.files[0];
    let reader = new FileReader();

    reader.onload = function (event) {
        let base64String = event.target.result;
        console.log(base64String);
        document.getElementById('imagem').src = base64String;
    };

    reader.readAsDataURL(file);
});