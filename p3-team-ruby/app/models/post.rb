# app/models/post.rb
class Post < ApplicationRecord
  belongs_to :user
  has_many :votes, dependent: :destroy
  has_many :comments, dependent: :destroy
  has_one_attached :image
  validates :body, presence: true
end
