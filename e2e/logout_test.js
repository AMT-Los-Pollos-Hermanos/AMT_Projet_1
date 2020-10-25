Feature('logout');

Before(({login}) => {
    login('user')
});

Scenario('test logout', ({ I }) => {
    I.amOnPage('/')
    I.click('Gil Balsiger')
    I.click('Déconnexion')
    I.seeInCurrentUrl('/login')
    I.see('Vous êtes maintenant déconnecté.')
    I.amOnPage('/question/abdbc27f-d54f-417c-b576-07f1c3cfd301')
    I.seeInCurrentUrl('/login')
});


Scenario('test logout from question page', ({ I }) => {
    I.amOnPage('/question/abdbc27f-d54f-417c-b576-07f1c3cfd301')
    I.click('Gil Balsiger')
    I.click('Déconnexion')
    I.seeInCurrentUrl('/login')
    I.see('Vous êtes maintenant déconnecté.')
    I.amOnPage('/question/abdbc27f-d54f-417c-b576-07f1c3cfd301')
    I.seeInCurrentUrl('/login')
});