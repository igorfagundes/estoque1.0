// script.js
document.addEventListener('DOMContentLoaded', () => {
    const tabs = document.querySelectorAll('nav ul li a');
    const tabContents = document.querySelectorAll('.tab-content');

    tabs.forEach(tab => {
        tab.addEventListener('click', event => {
            event.preventDefault();
            
            // Remove a classe 'active' de todas as abas
            tabContents.forEach(content => content.classList.remove('active'));

            // Adiciona a classe 'active' à aba selecionada
            const targetId = tab.getAttribute('href').substring(1);
            const targetContent = document.getElementById(targetId);
            targetContent.classList.add('active');
        });
    });

    // Mostrar a primeira aba por padrão
    if (tabs.length > 0) {
        tabs[0].click();
    }
});
