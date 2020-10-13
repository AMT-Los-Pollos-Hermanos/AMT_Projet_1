Feature('question');

Before(({login}) => {
    login('user');
});

Scenario('test add question', ({ I }) => {
    I.amOnPage('/questions')
    I.fillField('Titre', 'Quelle est le sens de la vie ?')
    I.fillField('.ck.ck-content.ck-editor__editable', '42')
    I.click('Ajouter')
    I.seeInCurrentUrl('/questions')
    I.see('Quelle est le sens de la vie ?', '.card-header')
    I.see('42', '.card-body')
});

Scenario('test add question failed', ({ I }) => {
    I.amOnPage('/questions')
    I.fillField('Titre', '')
    I.fillField('Contenu', '')
    I.click('Ajouter')
    I.seeInCurrentUrl('/questions')
    I.see('Une erreur s\'est produite lors de l\'ajout de votre question')
});