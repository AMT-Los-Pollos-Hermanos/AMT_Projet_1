Feature('registration');

Scenario('test registration success', (I) => {
    I.amOnPage('/login')
    I.see('Créer un nouveau compte')
    I.fillField('#rFirstName', 'Albert')
    I.fillField('#rLastName', 'Levert')
    I.fillField('#rEmail', 'albert.levert@gmail.com')
    I.fillField('#rUsername', 'albert')
    I.fillField('#rPassword', secret('albert'))
    I.click('S\'inscrire')
    I.seeInCurrentUrl('/login')
    I.see('Compte créé avec succès. Vous pouvez maintenant vous connecter.')
});

Scenario('test registration failed', (I) => {
    I.amOnPage('/login')
    I.see('Créer un nouveau compte')
    I.fillField('#rFirstName', 'Albert')
    I.fillField('#rLastName', 'Levert')
    I.fillField('#rEmail', 'albert.levert@gmail.com')
    I.fillField('#rUsername', 'gil')
    I.fillField('#rPassword', secret('albert'))
    I.click('S\'inscrire')
    I.seeInCurrentUrl('/login')
    I.see('Une erreur s\'est produite durant l\'enregistrement')
});
