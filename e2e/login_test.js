Feature('login');

Scenario('test correct login', (I) => {
    I.amOnPage('/login')
    I.see('Connexion')
    I.fillField('#lUsername', 'gil')
    I.fillField('#lPassword', secret('gil'))
    I.click('#loginBtn')
    I.see('Questions')
});

Scenario('test incorrect login', (I) => {
    I.amOnPage('/login')
    I.see('Connexion')
    I.fillField('#lUsername', 'gil')
    I.fillField('#lPassword', secret('abcd'))
    I.click('#loginBtn')
    I.see('Connexion')
});