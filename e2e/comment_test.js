Feature('comment');

Before(({login}) => {
    login('user');
});

Scenario('Comment for question', ({ I }) => {
    I.amOnPage('/comment/73dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Nouveau commentaire')
    I.see('Sur la question \'Question 1\'')
    I.seeElementInDOM('textarea')
});

Scenario('Comment for answer', ({ I }) => {
    I.amOnPage('/comment/35dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Nouveau commentaire')
    I.see('Sur la réponse de Gaëtan Daubresse')
    I.seeElementInDOM('textarea')
});

Scenario('Add comment for question', ({ I }) => {
    I.amOnPage('/comment/73dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.fillField('Commentaire', 'Mon super commentaire')
    I.click('Envoyer')
    I.seeInCurrentUrl('/question/73dbc27f-d54f-417c-b576-07f1c3cfd301')
    I.see('Mon super commentaire')
});