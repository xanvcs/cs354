Rails.application.routes.draw do
  devise_for :users
  resources :posts
  root 'posts#index'
  get 'posts/index'

  get "up" => "rails/health#show", as: :rails_health_check
  resources :posts do
    member do
      post 'upvote'
      post 'downvote'
    end
    resources :comments, only: [:create, :destroy]
  end
end
